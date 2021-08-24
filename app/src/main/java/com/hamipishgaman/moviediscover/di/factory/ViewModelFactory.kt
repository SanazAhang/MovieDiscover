package com.hamipishgaman.moviediscover.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf

class ViewModelFactory @Inject constructor(private val viewModels:MutableMap<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T
}