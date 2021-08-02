package com.rahmacom.rimesyarifix.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.rahmacom.rimesyarifix.data.network.ApiResponse;

import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class LiveDataCallAdapterFactory extends CallAdapter.Factory {

    @Nullable
    @Override
    public CallAdapter<?, ?> get(
            @NonNull Type returnType,
            @NonNull Annotation[] annotations,
            @NonNull Retrofit retrofit
    ) {
        if (getRawType(returnType) != LiveData.class) {
            return null;
        }

        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Type rawObservableType = getRawType(observableType);

        if (rawObservableType != ApiResponse.class) {
            throw new IllegalArgumentException("Type must be a resource");
        }

        if (!(observableType instanceof ParameterizedType)) {
            throw new IllegalArgumentException("Resource must be parameterized");
        }

        Type bodyType = getParameterUpperBound(0, (ParameterizedType) observableType);

        return new LiveDataCallAdapter<>(bodyType);
    }
}
