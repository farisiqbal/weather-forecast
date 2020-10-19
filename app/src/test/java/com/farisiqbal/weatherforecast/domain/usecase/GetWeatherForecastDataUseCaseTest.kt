package com.farisiqbal.weatherforecast.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
import com.farisiqbal.weatherforecast.domain.repository.WeatherForecastRepository
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * Created by farisiqbal on 19/10/2020
 */
class GetWeatherForecastDataUseCaseTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var useCase: GetWeatherForecastDataUseCase

    @Test
    fun invokeUseCase() {
        runBlocking {
            // GIVEN
            val mockData = mockk<WeatherForecastResponse>()
            val mockRepository = getMockRepository(mockData)
            useCase = GetWeatherForecastDataUseCase(mockRepository)

            // WHEN
            val result = useCase.invoke("query")

            // THEN
            assertEquals(result.peekData, mockData)
        }
    }

    private fun getMockRepository(data: WeatherForecastResponse) =
        object : WeatherForecastRepository {
            override suspend fun getWeatherForecastData(
                query: String,
                forecastDayCount: Int,
                units: String
            ): WeatherForecastResponse {
                return data
            }
        }
}