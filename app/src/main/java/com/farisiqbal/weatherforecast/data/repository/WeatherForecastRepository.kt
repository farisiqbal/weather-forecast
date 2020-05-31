package com.farisiqbal.weatherforecast.data.repository

import com.farisiqbal.weatherforecast.data.api.ApiResult
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse

/**
 * Created by farisiqbal on 30/05/2020
 */
interface WeatherForecastRepository {
    suspend fun getWeatherForecastData(
        query: String,
        forecastDayCount: Int = DEFAULT_FORECAST_DAYS_COUNT * THREE_HOURLY_DATA_TO_DAY_MULTIPLIER,
        units: String = DEFAULT_UNIT
    ) : ApiResult<WeatherForecastResponse>

    companion object {
        const val DEFAULT_FORECAST_DAYS_COUNT: Int = 4
        const val THREE_HOURLY_DATA_TO_DAY_MULTIPLIER: Int = 8 // since we don't use paid account to get real daily forecasts
        const val DEFAULT_UNIT: String = "metric"
    }
}