package com.rahmacom.rimesyarifix.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class FileRequestBody extends RequestBody {

    private int defaultBufferSize = 2048;
    private File fIle;
    private String contentType;
    private OnFileUploadCallback onFileUploadCallback;

    public FileRequestBody(int defaultBufferSize, File fIle, String contentType, OnFileUploadCallback onFileUploadCallback) {
        this.defaultBufferSize = defaultBufferSize;
        this.fIle = fIle;
        this.contentType = contentType;
        this.onFileUploadCallback = onFileUploadCallback;
    }

    public FileRequestBody(File fIle, String contentType) {
        this.fIle = fIle;
        this.contentType = contentType;
    }

    public FileRequestBody(File fIle, String contentType, OnFileUploadCallback onFileUploadCallback) {
        this.fIle = fIle;
        this.contentType = contentType;
        this.onFileUploadCallback = onFileUploadCallback;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public void writeTo(@NonNull BufferedSink bufferedSink) throws IOException {

    }

    public interface OnFileUploadCallback {
        void onUploading(int percent);
        void onFinish();
        void onHandleError();
    }
}
