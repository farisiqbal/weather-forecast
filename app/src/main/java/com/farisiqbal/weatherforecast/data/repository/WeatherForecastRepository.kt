package com.farisiqbal.weatherforecast.data.repository

import com.farisiqbal.weatherforecast.data.api.ApiResult
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse

/**
 * Created by farisiqbal on 30/05/2020
 */
interface WeatherForecastRepository {
    suspend fun getWeatherForecastData(
        query: String//,
        // forecastDayCount: Int,
        // units: String
    ) : ApiResult<WeatherForecastResponse>
}