package com.hamipishgaman.moviediscover.di

import com.hamipishgaman.moviediscover.domain.repository.movie.MovieRepository
import com.hamipishgaman.moviediscover.domain.usecase.movie.MovieGetUseCase
import com.hamipishgaman.moviediscover.domain.usecase.movie.MovieRefreshUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun refreshMovie(movieRepository: MovieRepository): MovieRefreshUseCase =
        MovieRefreshUseCase(
            movieRepository
        )

    @Singleton
    @Provides
    fun getMovie(movieRepository: MovieRepository): MovieGetUseCase =
        MovieGetUseCase(
            movieRepository
        )
}