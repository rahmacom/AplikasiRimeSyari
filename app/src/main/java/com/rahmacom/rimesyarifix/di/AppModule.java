package com.rahmacom.rimesyarifix.di;

import android.content.Context;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rahmacom.rimesyarifix.data.MainDataSource;
import com.rahmacom.rimesyarifix.data.local.AppDb;
import com.rahmacom.rimesyarifix.data.network.api.RimeSyariAPI;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.LiveDataCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Singleton
    @Provides
    public final RimeSyariAPI provideApiService() {
        final Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        return new Retrofit.Builder()
                .baseUrl(Const.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(RimeSyariAPI.class);
    }

    @Singleton
    @Provides
    public final AppDb provideDatabaseService(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDb.class, "rimesyari.db")
                .fallbackToDestructiveMigration()
                .build();
    }
}
