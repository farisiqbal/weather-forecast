package com.farisiqbal.weatherforecast.data.api.service

import com.farisiqbal.weatherforecast.data.api.interceptor.ApiInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by farisiqbal on 20/10/2020
 */
object ServiceBuilder {

    operator fun <T> invoke(serviceClass: Class<T>): T {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor())
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(serviceClass)
    }

    // sample request :
    // http://api.openweathermap.org/data/2.5/forecast?q=jakarta&cnt=5&units=metric&appId=8828e93aa68573092492094256559cb5
    private const val BASE_ENDPOINT: String = "https://api.openweathermap.org/"
    const val APP_ID: String = "8828e93aa68573092492094256559cb5"
}