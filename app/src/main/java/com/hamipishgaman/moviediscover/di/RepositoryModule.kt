package com.hamipishgaman.moviediscover.di

import com.hamipishgaman.moviediscover.domain.repository.movie.MovieRepository
import com.hamipishgaman.moviediscover.domain.repository.movie.MovieRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun movieRepository(
        movieRepositoryImp: MovieRepositoryImp
    ): MovieRepository

}