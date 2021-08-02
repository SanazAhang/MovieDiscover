package com.hamipishgaman.moviediscover.di

import com.hamipishgaman.moviediscover.data.datasource.movie.MovieLocalDataSource
import com.hamipishgaman.moviediscover.data.datasource.movie.MovieLocalDataSourceImp
import com.hamipishgaman.moviediscover.data.datasource.movie.MovieRemoteDataSource
import com.hamipishgaman.moviediscover.domain.repository.movie.MovieRepository
import com.hamipishgaman.moviediscover.domain.repository.movie.MovieRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
 class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(movieLocalDataSource: MovieLocalDataSource,movieRemoteDataSource: MovieRemoteDataSource):MovieRepository {
        return MovieRepositoryImp(movieLocalDataSource = movieLocalDataSource,
            movieRemoteSource = movieRemoteDataSource)
    }
}