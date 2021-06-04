package com.hamipishgaman.moviediscover.di

import com.hamipishgaman.moviediscover.data.datasource.movie.MovieLocalDataSource
import com.hamipishgaman.moviediscover.data.datasource.movie.MovieLocalDataSourceImp
import com.hamipishgaman.moviediscover.data.datasource.movie.MovieRemoteDataSource
import com.hamipishgaman.moviediscover.data.datasource.movie.MovieRemoteDataSourceImp
import com.hamipishgaman.moviediscover.data.local.MovieDao
import com.hamipishgaman.moviediscover.data.network.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun movieRemoteDataSource(movieApi: MovieApi): MovieRemoteDataSource =
        MovieRemoteDataSourceImp(movieApi)


    @Singleton
    @Provides
    fun movieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource =
        MovieLocalDataSourceImp(movieDao)
}
