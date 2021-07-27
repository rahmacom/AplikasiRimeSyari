package com.rahmacom.rimesyarifix.data.network;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Response;

public class ApiResponse<T> {

    public ApiResponse<T> create(Throwable error) {
        return new ApiErrorResponse<>(Objects.equals(error.getMessage(), "") ? error.getMessage()
                : "Unknown error");
    }

    public ApiResponse<T> create(Response<T> response) {
        if (response.isSuccessful()) {
            T body = response.body();
            if (body == null || response.code() == 204) {
                return new ApiEmptyResponse<>();
            } else {
                return new ApiSuccessResponse<>(body, response.message());
            }
        } else {
            String message = "";
            try {
                message = response.errorBody().string();
            } catch (IOException e) {
                e.printStackTrace();
                message = response.message();
            }
            return new ApiErrorResponse<>(message);
        }
    }


    public static class ApiErrorResponse<T> extends ApiResponse<T> {

        private String errorMessage;

        public ApiErrorResponse(String message) {
            this.errorMessage = message;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

    public static class ApiSuccessResponse<T> extends ApiResponse<T> {

        private T body;
        private String message;

        public ApiSuccessResponse(T body, String message) {
            this.body = body;
            this.message = message;
        }

        public T getBody() {
            return body;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class ApiEmptyResponse<T> extends ApiResponse<T> { }
}
