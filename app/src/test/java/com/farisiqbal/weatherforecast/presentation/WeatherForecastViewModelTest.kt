package com.farisiqbal.weatherforecast.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.farisiqbal.weatherforecast.data.api.ResultLoad
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
import com.farisiqbal.weatherforecast.domain.repository.WeatherForecastRepository
import com.farisiqbal.weatherforecast.domain.usecase.GetWeatherForecastDataUseCase
import com.farisiqbal.weatherforecast.presentation.forecast.WeatherForecastViewModel
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by farisiqbal on 31/05/2020
 */
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class WeatherForecastViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: WeatherForecastViewModel
    private lateinit var useCase: GetWeatherForecastDataUseCase

    // observers
    private lateinit var forecastResultLoadObserver: Observer<ResultLoad<WeatherForecastResponse>>

    @Before
    fun setUp() {
        useCase = mockk()
        viewModel = spyk(
            WeatherForecastViewModel(useCase, Dispatchers.Unconfined)
        )
    }

    @Test
    fun `setNewQuery would update query`() {
        // GIVEN
        val newQuery = "a new query"

        // WHEN
        viewModel.setNewQuery(newQuery)

        // THEN
        assertEquals(viewModel.query, newQuery)
    }

    @Test
    fun `getForecastData would set state to loading and update the result upon completion`() {
        runBlockingTest {
            // GIVEN
            setupObservers()
            val mockResult = ResultLoad.Success(mockk<WeatherForecastResponse>())
            setMockResult(mockResult)

            // WHEN
            viewModel.getForecastData()

            // THEN
            forecastResultLoadObserver.onChanged(ResultLoad.Loading)
            forecastResultLoadObserver.onChanged(mockResult)
        }
    }

    // supporting functions
    private fun setupObservers() {
        forecastResultLoadObserver = mockk(relaxed = true)
        viewModel.weatherDataResult.observeForever(forecastResultLoadObserver)
    }

    private fun setMockResult(result: ResultLoad<WeatherForecastResponse>) {
        coEvery {
            useCase.invoke(viewModel.query, any(), any())
        } returns result
    }
}