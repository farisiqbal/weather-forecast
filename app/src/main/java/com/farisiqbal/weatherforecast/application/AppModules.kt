package com.farisiqbal.weatherforecast.application

import com.farisiqbal.weatherforecast.data.api.service.ForecastsService
import com.farisiqbal.weatherforecast.data.api.service.ServiceBuilder
import com.farisiqbal.weatherforecast.domain.repository.WeatherForecastRepository
import com.farisiqbal.weatherforecast.data.repository.WeatherForecastRepositoryImpl
import com.farisiqbal.weatherforecast.domain.usecase.GetWeatherForecastDataUseCase
import com.farisiqbal.weatherforecast.presentation.forecast.WeatherForecastViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by farisiqbal on 31/05/2020
 */
val apiModule = module {
    single {
        ServiceBuilder(ForecastsService::class.java)
    }
}

val repositoryModule = module {
    single { WeatherForecastRepositoryImpl(get()) as WeatherForecastRepository }
}

val useCaseModule = module {
    single { GetWeatherForecastDataUseCase(get()) }
}

val viewModelModule = module {
    viewModel {
        WeatherForecastViewModel(
            get(),
            Dispatchers.Main
        )
    }
}