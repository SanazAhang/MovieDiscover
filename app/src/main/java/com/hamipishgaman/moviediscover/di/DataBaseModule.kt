package com.hamipishgaman.moviediscover.di

import android.content.Context
import androidx.room.Room
import com.hamipishgaman.moviediscover.data.local.AppDataBase
import com.hamipishgaman.moviediscover.data.local.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun movieDataBase(@ApplicationContext context: Context): AppDataBase =
        Room.databaseBuilder(
            context,
            AppDataBase::class.java, "database"
        ).build()

    @Singleton
    @Provides
    fun movieDao(appDataBase: AppDataBase): MovieDao =
        appDataBase.movieDao()
}