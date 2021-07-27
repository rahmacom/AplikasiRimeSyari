package com.rahmacom.rimesyarifix.data;

import com.rahmacom.rimesyarifix.data.local.LocalRepository;
import com.rahmacom.rimesyarifix.data.network.RemoteRepository;
import com.rahmacom.rimesyarifix.utils.AppExecutors;

import javax.inject.Singleton;

@Singleton
public class MainRepository {

    private AppExecutors appExecutors;
    private RemoteRepository remoteRepo;
    private LocalRepository localRepo;
}
