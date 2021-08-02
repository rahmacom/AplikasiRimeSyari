package com.rahmacom.rimesyarifix.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.rahmacom.rimesyarifix.data.network.ApiResponse;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<ApiResponse<R>>> {

    private final Type responseType;

    public LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @NonNull
    @Override
    public Type responseType() {
        return this.responseType;
    }

    @NonNull
    @Override
    public LiveData<ApiResponse<R>> adapt(@NonNull Call<R> call) {
        return (new LiveData<ApiResponse<R>>() {

            private final AtomicBoolean isStarted = new AtomicBoolean(false);

            @Override
            protected void onActive() {
                super.onActive();
                if (isStarted.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(@NonNull Call<R> call, @NonNull Response<R> response) {
                            postValue(ApiResponse.create(response));
                        }

                        @Override
                        public void onFailure(@NonNull Call<R> call, @NonNull Throwable error) {
                            postValue(ApiResponse.create(error));
                        }
                    });
                }
            }
        });
    }
}
