package com.hamipishgaman.moviediscover.di

import com.hamipishgaman.moviediscover.data.datasource.movie.MovieLocalDataSource
import com.hamipishgaman.moviediscover.data.datasource.movie.MovieLocalDataSourceImp
import com.hamipishgaman.moviediscover.data.datasource.movie.MovieRemoteDataSource
import com.hamipishgaman.moviediscover.data.datasource.movie.MovieRemoteDataSourceImp
import com.hamipishgaman.moviediscover.data.local.MovieDao
import com.hamipishgaman.moviediscover.data.network.api.MovieApi
import dagger.Provides

class DataSourceModule {

    @Provides
    fun provideLocalDataSource(movieDao: MovieDao):MovieLocalDataSource {
        return MovieLocalDataSourceImp(movieDao)
    }

    @Provides
    fun provideRemoteDataSource(movieApi: MovieApi):MovieRemoteDataSource{
        return MovieRemoteDataSourceImp(movieApi)
    }
}