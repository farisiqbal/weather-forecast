package com.farisiqbal.weatherforecast.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.farisiqbal.weatherforecast.data.repository.WeatherForecastRepository
import com.farisiqbal.weatherforecast.data.repository.WeatherForecastRepositoryImpl

/**
 * Created by farisiqbal on 30/05/2020
 */
class WeatherForecastViewModelFactory(
    private val repository: WeatherForecastRepository = WeatherForecastRepositoryImpl()
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherForecastViewModel(repository) as T
    }
}