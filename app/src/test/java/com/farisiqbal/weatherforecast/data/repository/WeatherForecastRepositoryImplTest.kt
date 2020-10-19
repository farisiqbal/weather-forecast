package com.farisiqbal.weatherforecast.data.repository

import com.farisiqbal.weatherforecast.data.api.ApiService
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
import com.farisiqbal.weatherforecast.domain.repository.WeatherForecastRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by farisiqbal on 31/05/2020
 */
@RunWith(JUnit4::class)
class WeatherForecastRepositoryImplTest {

    private lateinit var repository: WeatherForecastRepository
    private lateinit var service: ApiService

    @Before
    fun setUp() {
        service = mockk()
        repository = WeatherForecastRepositoryImpl(service)
    }

    @Test
    fun getWeatherForecastData() {
        runBlocking {
            // GIVEN
            val mockResponse = mockk<WeatherForecastResponse>()
            coEvery {
                service.getForecasts(any(), any(), any())
            } returns mockResponse

            // WHEN
            val result = repository.getWeatherForecastData("a query", 123, "a unit")

            // THEN
            assertEquals(result.peekData, mockResponse)
        }
    }
}