package com.hamipishgaman.moviediscover.di

import com.hamipishgaman.moviediscover.BuildConfig
import com.hamipishgaman.moviediscover.data.network.api.MovieApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun movieApi(retrofit: Retrofit):MovieApi =
        retrofit.create()


    @Singleton
    @Provides
    fun retrofit(okHttpClient: OkHttpClient,moshiConverterFactory: MoshiConverterFactory):Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()


    @Singleton
    @Provides
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor):OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .build()


    @Singleton
    @Provides
    fun moshiConvertor():MoshiConverterFactory =
        MoshiConverterFactory.create()


    @Singleton
    @Provides
    fun httpLoginInterceptor():HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

}