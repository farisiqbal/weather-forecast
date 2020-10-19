package com.farisiqbal.weatherforecast.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.farisiqbal.weatherforecast.data.api.ResultLoad
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
import com.farisiqbal.weatherforecast.data.repository.WeatherForecastRepository
import com.farisiqbal.weatherforecast.presentation.forecast.WeatherForecastViewModel
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by farisiqbal on 31/05/2020
 */
@RunWith(JUnit4::class)
class WeatherForecastViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: WeatherForecastViewModel
    private lateinit var repository: WeatherForecastRepository

    // observers
    private lateinit var forecastsObserver: Observer<WeatherForecastResponse>
    private lateinit var loadingObserver: Observer<Boolean>
    private lateinit var errorObserver: Observer<Boolean>

    @Before
    fun setUp() {
        repository = mockk()
        viewModel =
            WeatherForecastViewModel(
                repository,
                Dispatchers.Unconfined
            )
    }

    @Test
    fun `setNewQuery would update query`() {
        // GIVEN
        viewModel.query = "previous query"
        val newQuery = "a new query"

        // WHEN
        viewModel.setNewQuery(newQuery)

        // THEN
        assertEquals(viewModel.query, newQuery)
    }

    @Test
    fun `getForecastData failed`() {
        // GIVEN
        coEvery {
            repository.getWeatherForecastData(viewModel.query, any(), any())
        } returns ResultLoad.Error("error_message", -1)
        setupObservers()

        // WHEN
        viewModel.getForecastData()

        // THEN
        verifyLoadingStarted()
        verify {
            errorObserver.onChanged(true)
            loadingObserver.onChanged(false)
        }
        verifyObservers()
    }

    @Test
    fun `getForecastData success`() {
        // GIVEN
        val mockResponse: WeatherForecastResponse = mockk()
        coEvery {
            repository.getWeatherForecastData(viewModel.query, any(), any())
        } returns ResultLoad.Success(mockResponse)
        setupObservers()

        // WHEN
        viewModel.getForecastData()

        // THEN
        verifyLoadingStarted()
        verify {
            forecastsObserver.onChanged(mockResponse)
            loadingObserver.onChanged(false)
        }
        verifyObservers()
    }

    // supporting functions
    private fun setupObservers() {
        forecastsObserver = mockk(relaxed = true)
        loadingObserver = mockk(relaxed = true)
        errorObserver = mockk(relaxed = true)

        viewModel.run {
            weatherForecastResponse.observeForever(forecastsObserver)
            isLoading.observeForever(loadingObserver)
            isError.observeForever(errorObserver)
        }
    }

    private fun verifyObservers() {
        confirmVerified(
            forecastsObserver,
            loadingObserver,
            errorObserver
        )
    }

    private fun verifyLoadingStarted() {
        verify {
            errorObserver.onChanged(false)
            loadingObserver.onChanged(true)
        }
    }
}