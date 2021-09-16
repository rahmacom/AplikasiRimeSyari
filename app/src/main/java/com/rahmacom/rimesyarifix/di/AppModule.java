package com.rahmacom.rimesyarifix.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rahmacom.rimesyarifix.data.network.api.RimeSyariAPI;
import com.rahmacom.rimesyarifix.utils.Const;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Singleton
    @Provides
    public static RimeSyariAPI provideApiService() {
        final Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        final Converter.Factory gsonFactory =
                GsonConverterFactory.create(gson);

        return new Retrofit.Builder()
                .baseUrl(Const.BASE_API_URL)
                .addConverterFactory(gsonFactory)
                .build()
                .create(RimeSyariAPI.class);
    }
}
