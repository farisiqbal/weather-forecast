package com.farisiqbal.weatherforecast.di

import com.farisiqbal.weatherforecast.data.api.ApiService
import com.farisiqbal.weatherforecast.data.repository.WeatherForecastRepositoryImpl
import com.farisiqbal.weatherforecast.view.WeatherForecastViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by farisiqbal on 31/05/2020
 */
val weatherForecastModule = module {
    single { ApiService() }
    single { WeatherForecastRepositoryImpl(get()) }
    viewModel { WeatherForecastViewModel(get(), get()) }
}