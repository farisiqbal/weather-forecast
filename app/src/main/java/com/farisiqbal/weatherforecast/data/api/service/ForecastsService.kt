package com.farisiqbal.weatherforecast.data.api.service

import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by farisiqbal on 30/05/2020
 */
interface ForecastsService {

    @GET("data/2.5/forecast")
    suspend fun getForecasts(
        @Query("q") query: String,
        @Query("cnt") count: Int,
        @Query("units") page: String
    ): WeatherForecastResponse
}