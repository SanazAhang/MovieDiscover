package com.hamipishgaman.moviediscover.di

import android.content.Context
import android.provider.DocumentsContract
import androidx.room.Room
import com.hamipishgaman.moviediscover.data.local.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun movieDataBase(@ApplicationContext context: Context) :AppDataBase =
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,"database"
        ).build()

    @Provides
    @Singleton
    fun movieDao(appDataBase:AppDataBase) =
        appDataBase.movieDao()
}