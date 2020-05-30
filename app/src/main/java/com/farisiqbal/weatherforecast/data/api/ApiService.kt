package com.farisiqbal.weatherforecast.data.api

import com.farisiqbal.weatherforecast.data.api.interceptor.ApiInterceptor
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by farisiqbal on 30/05/2020
 */
interface ApiService {
    companion object {
        // sample request :
        // http://api.openweathermap.org/data/2.5/forecast?q=jakarta&cnt=5&units=metric&appId=8828e93aa68573092492094256559cb5
        private const val BASE_ENDPOINT: String = "https://api.openweathermap.org/"
        const val APP_ID: String = "8828e93aa68573092492094256559cb5"
        const val DEFAULT_FORECAST_DAYS_COUNT: Int = 4
        const val THREE_HOURLY_DATA_TO_DAY_MULTIPLIER: Int = 8 // since we don't use paid account to get real daily forecasts
        const val DEFAULT_UNIT: String = "metric"

        operator fun invoke(): ApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(ApiInterceptor())
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

        // error messages
        const val ERROR_DEFAULT = "Request did not succeed"
        const val ERROR_NO_NETWORK = "No network connection"
    }

    @GET("data/2.5/forecast")
    suspend fun getForecasts(
        @Query("q") query: String,
        @Query("cnt") count: Int = DEFAULT_FORECAST_DAYS_COUNT * THREE_HOURLY_DATA_TO_DAY_MULTIPLIER,
        @Query("units") page: String = DEFAULT_UNIT
    ): WeatherForecastResponse
}