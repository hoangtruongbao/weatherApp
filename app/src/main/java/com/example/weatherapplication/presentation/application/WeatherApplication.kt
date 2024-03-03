package com.example.weatherapplication.presentation.application

import android.annotation.SuppressLint
import android.app.Application
import com.example.weatherapplication.presentation.helper.SharedPreferenceHelper
import com.example.weatherapplication.presentation.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        prefHelper = SharedPreferenceHelper(this)
        startKoin {
            androidContext(this@WeatherApplication)
            modules(
                commonModules +
                        viewmodels +
                        repositoryModules +
                        dataSourceModule +
                        usecaseModules +
                        networkModule +
                        prefModule
            )
        }
        instance = this

    }


    companion object {
        lateinit var instance: WeatherApplication
            private set

        @SuppressLint("StaticFieldLeak")
        lateinit var prefHelper: SharedPreferenceHelper
            private set

    }
}

