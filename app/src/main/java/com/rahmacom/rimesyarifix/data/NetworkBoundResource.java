package com.rahmacom.rimesyarifix.data;


import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.rahmacom.rimesyarifix.data.network.ApiResponse;
import com.rahmacom.rimesyarifix.data.vo.Resource;
import com.rahmacom.rimesyarifix.utils.AppExecutors;

import java.util.Objects;

public abstract class NetworkBoundResource<ResultType, RequestType> {

    private final AppExecutors appExecutors;

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    public NetworkBoundResource(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        result.setValue(Resource.loading(null));

        LiveData<ResultType> dbSource = loadFromDb();

        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource, newData -> setValue(Resource.success(newData)));
            }
        });
    }

    @MainThread
    private void setValue(Resource<ResultType> newValue) {
        if (!Objects.equals(result.getValue(), newValue)) {
            result.setValue(newValue);
        }
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        LiveData<ApiResponse<RequestType>> apiResponse = createCall();

        result.addSource(dbSource, newData -> setValue(Resource.loading(newData)));
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            result.removeSource(dbSource);

            if (response instanceof ApiResponse.ApiSuccessResponse) {
                appExecutors.diskIO().execute(() -> {
                    saveCallResult(processResponse((ApiResponse.ApiSuccessResponse<RequestType>) response));
                    appExecutors.mainThread().execute(() -> {
                        result.addSource(loadFromDb(), newData -> {
                            setValue(Resource.success(newData));
                        });
                    });
                });
            } else if (response instanceof ApiResponse.ApiErrorResponse) {
                onFetchedFailed();
                result.addSource(dbSource, newData -> {
                    setValue(Resource.error(((ApiResponse.ApiErrorResponse<RequestType>) response).getErrorMessage(), newData));
                });
            } else if (response instanceof ApiResponse.ApiEmptyResponse) {
                appExecutors.mainThread().execute(() -> {
                    result.addSource(loadFromDb(), newData -> {
                        setValue(Resource.empty(newData));
                    });
                });
            }
        });
    }

    protected void onFetchedFailed() {

    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }

    @WorkerThread
    protected RequestType processResponse(ApiResponse.ApiSuccessResponse<RequestType> response) {
        return response.getBody();
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    @MainThread
    protected abstract boolean shouldFetch(@Nullable ResultType data);

    @NonNull
    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();
}
