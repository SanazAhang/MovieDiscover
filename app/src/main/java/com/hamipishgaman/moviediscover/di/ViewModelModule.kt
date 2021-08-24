package com.hamipishgaman.moviediscover.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hamipishgaman.moviediscover.di.factory.ViewModelFactory
import com.hamipishgaman.moviediscover.di.factory.ViewModelKey
import com.hamipishgaman.moviediscover.ui.movielist.ListMovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory:ViewModelFactory):ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ListMovieViewModel::class)
    abstract fun movieViewModel(viewModel:ListMovieViewModel): ViewModel
}