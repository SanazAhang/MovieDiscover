package com.hamipishgaman.moviediscover

import android.app.Application
import com.hamipishgaman.moviediscover.di.AppComponent

class MovieApplication : Application(){
    val component: AppComponent by lazy {
      DaggerAppComponent
          .factory
          .create(applicationContext)
    }
}