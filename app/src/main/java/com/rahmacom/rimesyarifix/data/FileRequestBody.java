package com.rahmacom.rimesyarifix.data;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class FileRequestBody extends RequestBody {

    private static final int DEFAULT_BUFFER_SIZE = 2048;
    private final File fIle;
    private final String contentType;
    private final OnFileUploadCallback onFileUploadCallback;

    public FileRequestBody(File fIle, String contentType, OnFileUploadCallback onFileUploadCallback) {
        this.fIle = fIle;
        this.contentType = contentType;
        this.onFileUploadCallback = onFileUploadCallback;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return MediaType.parse(contentType + "/*");
    }

    @Override
    public long contentLength() {
        return fIle.length();
    }

    @Override
    public void writeTo(@NonNull BufferedSink bufferedSink) throws IOException {
        long length = fIle.length();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];

        try (FileInputStream fileInputStream = new FileInputStream(fIle)) {
            long uploaded = 0;
            int read;
            Handler handler = new Handler(Looper.getMainLooper());

            while ((read = fileInputStream.read(buffer)) != -1) {
                handler.post(new ProgressUpdater(uploaded, length));

                uploaded += read;
                bufferedSink.write(buffer, 0, read);
            }
        }
    }

    public interface OnFileUploadCallback {
        void onUploading(int percent);
        void onFinish();
        void onHandleError();
    }

    class ProgressUpdater implements Runnable {
        long upload;
        long total;

        public ProgressUpdater(long upload, long total) {
            this.upload = upload;
            this.total = total;
        }

        @Override
        public void run() {
            onFileUploadCallback.onUploading((int) (100 * upload / total));
        }
    }
}
