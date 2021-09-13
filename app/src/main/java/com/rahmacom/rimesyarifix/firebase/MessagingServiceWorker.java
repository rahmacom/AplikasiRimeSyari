package com.rahmacom.rimesyarifix.firebase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import timber.log.Timber;

public class MessagingServiceWorker extends Worker {

    public MessagingServiceWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Timber.d("Perform long running task in scheduled job...");
        return Result.success();
    }
}
