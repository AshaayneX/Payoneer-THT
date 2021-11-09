package com.payoneer.di;

import com.payoneer.data.PayoneerApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Hilt module, contains Singleton builder pattern objects
 */
@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    /** provide retrofit instance */
    @Provides
    @Singleton
    public static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(PayoneerApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    /** provide Api interface */
    @Provides
    @Singleton
    public static PayoneerApi providePayoneerApi(Retrofit retrofit) {
        return retrofit.create(PayoneerApi.class);
    }
}
