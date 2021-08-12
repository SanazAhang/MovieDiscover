package com.hamipishgaman.moviediscover.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun provideDao(){

    }
}