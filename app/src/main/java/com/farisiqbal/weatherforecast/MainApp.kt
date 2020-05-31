package com.farisiqbal.weatherforecast

import android.app.Application
import com.farisiqbal.weatherforecast.di.weatherForecastModule
import org.koin.core.context.startKoin

/**
 * Created by farisiqbal on 31/05/2020
 */
class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(weatherForecastModule)
        }
    }
}