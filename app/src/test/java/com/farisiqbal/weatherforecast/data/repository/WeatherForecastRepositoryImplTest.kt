package com.farisiqbal.weatherforecast.data.repository

import com.farisiqbal.weatherforecast.data.api.ApiService
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
import com.farisiqbal.weatherforecast.domain.repository.WeatherForecastRepository
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by farisiqbal on 31/05/2020
 */
class WeatherForecastRepositoryImplTest {

    private lateinit var repository: WeatherForecastRepository

    @Test
    fun getWeatherForecastData() {
        runBlocking {
            // GIVEN
            val mockResponse = mockk<WeatherForecastResponse>()
            val mockService = getMockService(mockResponse)
            repository = WeatherForecastRepositoryImpl(mockService)

            // WHEN
            val result = repository.getWeatherForecastData("a query", 123, "a unit")

            // THEN
            assertEquals(result, mockResponse)
        }
    }

    private fun getMockService(response: WeatherForecastResponse) = object : ApiService {
        override suspend fun getForecasts(
            query: String,
            count: Int,
            page: String
        ): WeatherForecastResponse {
            return response
        }
    }
}