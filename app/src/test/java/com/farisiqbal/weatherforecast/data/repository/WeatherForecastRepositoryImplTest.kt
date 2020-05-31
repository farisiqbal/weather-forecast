package com.farisiqbal.weatherforecast.data.repository

import com.farisiqbal.weatherforecast.data.api.ApiService
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
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
        // GIVEN
        val mockQuery = "a query"
        val mockCount = 12
        val mockUnit = "a unit"

        // WHEN
        runBlockingTest {
            repository.getWeatherForecastData(mockQuery, mockCount, mockUnit)
        }

        // THEN
        coVerify {
            service.getForecasts(mockQuery, mockCount, mockUnit)
        }
    }
}