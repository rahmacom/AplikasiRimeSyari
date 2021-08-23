package com.rahmacom.rimesyarifix.data.vo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    private final Status status;

    private final String message;

    private final T data;

    public Resource(@NonNull Status status, @Nullable String message, @Nullable T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    @NonNull
    public Status getStatus() {
        return status;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    @Nullable
    public T getData() {
        return data;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource(Status.SUCCESS, null, data);
    }

    public static <T> Resource<T> error(String message, @Nullable T data) {
        return new Resource(Status.ERROR, message, data);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource(Status.LOADING, null, data);
    }

    public static <T> Resource<T> empty(@Nullable T data) {
        return new Resource(Status.EMPTY, null, data);
    }

    public static <T> Resource<T> invalid(@Nullable String message) {
        return new Resource(Status.INVALID, message, null);
    }

    public static <T> Resource<T> unauthorized(String message) {
        return new Resource(Status.UNAUTHORIZED, message, null);
    }

    public static <T> Resource<T> forbidden(String message) {
        return new Resource(Status.FORBIDDEN, message, null);
    }

    public static <T> Resource<T> unprocessableEntity(String message, @Nullable T data) {
        return new Resource(Status.UNPROCESSABLE_ENTITY, message, data);
    }
}
