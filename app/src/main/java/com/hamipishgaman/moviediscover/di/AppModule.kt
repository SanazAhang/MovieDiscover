package com.hamipishgaman.moviediscover.di

import android.app.Application
import com.hamipishgaman.moviediscover.MovieApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val movieApplication: MovieApplication) {

    @Provides
    @Singleton
    fun provideApp() = movieApplication
}