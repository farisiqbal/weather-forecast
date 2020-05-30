package com.farisiqbal.weatherforecast.data.repository

import com.farisiqbal.weatherforecast.data.api.ApiResult
import com.farisiqbal.weatherforecast.data.api.ApiService
import com.farisiqbal.weatherforecast.data.api.SafeApiRequest
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
import kotlinx.coroutines.Dispatchers

/**
 * Created by farisiqbal on 30/05/2020
 */
class WeatherForecastRepositoryImpl(
    private val service: ApiService = ApiService()
) : WeatherForecastRepository, SafeApiRequest {
    override suspend fun getWeatherForecastData(
        query: String//,
        // forecastDayCount: Int,
        // units: String
    ): ApiResult<WeatherForecastResponse> {
        return safeApiCall(Dispatchers.IO) {
            service.getForecasts(query)
        }
    }
}