package com.farisiqbal.weatherforecast.domain.usecase

import com.farisiqbal.weatherforecast.data.api.ResultLoad
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
import com.farisiqbal.weatherforecast.domain.repository.WeatherForecastRepository
import kotlinx.coroutines.Dispatchers

/**
 * Created by farisiqbal on 19/10/2020
 */
class GetWeatherForecastDataUseCase(
    private val repository: WeatherForecastRepository
): BaseUseCase() {

    suspend operator fun invoke(
        query: String,
        forecastDayCount: Int = DEFAULT_FORECAST_ITEM_COUNT,
        units: String = DEFAULT_UNIT
    ): ResultLoad<WeatherForecastResponse> {
        return safeApiCall(Dispatchers.IO) {
            repository.getWeatherForecastData(query, forecastDayCount, units)
        }
    }

    companion object {
        const val DEFAULT_FORECAST_ITEM_COUNT: Int = 24
        const val DEFAULT_UNIT: String = "metric"
    }
}