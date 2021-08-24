package com.hamipishgaman.moviediscover.di

import com.hamipishgaman.moviediscover.ui.MainActivity
import dagger.Component
import kotlin.reflect.jvm.internal.impl.load.java.lazy.SingleModuleClassResolver

@Component(
    modules = [AppModule::class,
        RepositoryModule::class,
        DataSourceModule::class,
        NetworkModule::class,
        PersistenceModule::class]
)
interface AppComponent {
    fun inject(activity: MainActivity)
}