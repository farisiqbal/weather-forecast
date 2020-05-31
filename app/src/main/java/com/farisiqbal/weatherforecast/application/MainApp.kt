package com.farisiqbal.weatherforecast.application

import android.app.Application
import org.koin.core.context.startKoin

/**
 * Created by farisiqbal on 31/05/2020
 */
class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(
                apiModule,
                repositoryModule,
                viewModelModule
            ))
        }
    }
}