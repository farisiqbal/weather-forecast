package com.farisiqbal.weatherforecast.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farisiqbal.weatherforecast.data.api.ApiResult
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
import com.farisiqbal.weatherforecast.data.repository.WeatherForecastRepository
import com.farisiqbal.weatherforecast.data.repository.WeatherForecastRepositoryImpl
import kotlinx.coroutines.launch

class WeatherForecastViewModel(
    private val repository: WeatherForecastRepository = WeatherForecastRepositoryImpl()
) : ViewModel() {

    // main view states
    val weatherForecastResponse = MutableLiveData<WeatherForecastResponse>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()
    private var query: String = ""

    fun setNewQuery(newQuery: String) {
        if (newQuery != query) {
            query = newQuery
        }
    }

    fun getForecastData() {
        isError.value = false
        isLoading.value = true
        viewModelScope.launch {
            val result = repository.getWeatherForecastData(query)
            if (result is ApiResult.Success) {
                weatherForecastResponse.value = result.value
            } else {
                isError.value = true
            }
            isLoading.value = false
        }
    }
}
