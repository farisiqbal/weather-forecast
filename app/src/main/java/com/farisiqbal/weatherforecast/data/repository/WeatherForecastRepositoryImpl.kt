package com.farisiqbal.weatherforecast.data.repository

import com.farisiqbal.weatherforecast.data.api.service.ForecastsService
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
import com.farisiqbal.weatherforecast.domain.repository.WeatherForecastRepository

/**
 * Created by farisiqbal on 30/05/2020
 */
class WeatherForecastRepositoryImpl(
    private val service: ForecastsService
) : WeatherForecastRepository {
    override suspend fun getWeatherForecastData(
        query: String,
        forecastDayCount: Int,
        units: String
    ): WeatherForecastResponse {
        return service.getForecasts(query, forecastDayCount, units)
    }
}