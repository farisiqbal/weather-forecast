package com.farisiqbal.weatherforecast.domain.usecase

import com.farisiqbal.weatherforecast.data.api.ResultLoad
import com.farisiqbal.weatherforecast.data.api.SafeApiRequest
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
import com.farisiqbal.weatherforecast.domain.repository.WeatherForecastRepository
import kotlinx.coroutines.Dispatchers

/**
 * Created by farisiqbal on 19/10/2020
 */
class GetWeatherForecastDataUseCase(
    private val repository: WeatherForecastRepository
): SafeApiRequest {

    suspend operator fun invoke(
        query: String,
        forecastDayCount: Int = DEFAULT_FORECAST_DAYS_COUNT,
        units: String = DEFAULT_UNIT
    ): ResultLoad<WeatherForecastResponse> {
        return safeApiCall(Dispatchers.IO) {
            repository.getWeatherForecastData(query, forecastDayCount, units)
        }
    }

    companion object {
        const val DEFAULT_FORECAST_DAYS_COUNT: Int = 4
        const val DEFAULT_UNIT: String = "metric"
    }
}