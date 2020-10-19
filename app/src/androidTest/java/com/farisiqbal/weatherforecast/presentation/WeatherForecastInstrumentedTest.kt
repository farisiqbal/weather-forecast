package com.farisiqbal.weatherforecast.presentation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.farisiqbal.weatherforecast.R
import com.farisiqbal.weatherforecast.data.api.ResultLoad
import com.farisiqbal.weatherforecast.data.api.response.City
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecast
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
import com.farisiqbal.weatherforecast.domain.repository.WeatherForecastRepository
import com.farisiqbal.weatherforecast.presentation.forecast.WeatherForecastFragment
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by farisiqbal on 31/05/2020
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class WeatherForecastInstrumentedTest {

    private val repository: WeatherForecastRepository = mockk()

    @Test
    fun load_data_success_display_correctly() {
        // GIVEN
        val mockCity = mockk<City>(relaxed = true)
        val mockForecasts = listOf(mockk<WeatherForecast>(relaxed = true))
        coEvery {
            repository.getWeatherForecastData(any(), any(), any())
        } returns ResultLoad.Success(
            WeatherForecastResponse(
                city = mockCity,
                cnt = 4,
                cod = 200,
                list = mockForecasts,
                message = ""
            )
        )

        // WHEN
        launchFragment()

        // THEN
        Espresso.onView(ViewMatchers.withId(R.id.layoutMain))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvCurrentLocation))
            .check(ViewAssertions.matches(ViewMatchers.withText(mockCity.name)))
        Espresso.onView(ViewMatchers.withText(R.id.layoutError))
            .check(ViewAssertions.doesNotExist())
    }

    private fun launchFragment() {
        launchFragmentInContainer<WeatherForecastFragment>(themeResId = R.style.Theme_AppCompat)
    }
}