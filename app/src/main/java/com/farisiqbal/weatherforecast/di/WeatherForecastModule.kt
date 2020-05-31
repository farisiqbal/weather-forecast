package com.farisiqbal.weatherforecast.di

import com.farisiqbal.weatherforecast.data.api.ApiService
import org.koin.dsl.module.module

/**
 * Created by farisiqbal on 31/05/2020
 */
val weatherForecastModule = module {
    single { ApiService() }
}