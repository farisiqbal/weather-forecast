package com.farisiqbal.weatherforecast.data.repository

import com.farisiqbal.weatherforecast.data.api.ApiResult
import com.farisiqbal.weatherforecast.data.api.ApiService
import com.farisiqbal.weatherforecast.data.api.SafeApiRequest
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
import com.farisiqbal.weatherforecast.utils.getDailyData
import kotlinx.coroutines.Dispatchers

/**
 * Created by farisiqbal on 30/05/2020
 */
class WeatherForecastRepositoryImpl(
    private val service: ApiService
) : WeatherForecastRepository, SafeApiRequest {
    override suspend fun getWeatherForecastData(
        query: String,
        forecastDayCount: Int,
        units: String
    ): ApiResult<WeatherForecastResponse> {
        return safeApiCall(Dispatchers.IO) {
            service.getForecasts(query, forecastDayCount, units).let {
                // modify 3-hourly data to get daily data
                WeatherForecastResponse(
                    city = it.city,
                    cnt = it.cnt,
                    list = it.list.getDailyData(),
                    cod = it.cod,
                    message = it.message
                )
            }
        }
    }
}