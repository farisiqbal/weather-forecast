package com.farisiqbal.weatherforecast.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farisiqbal.weatherforecast.data.api.ApiResult
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
import com.farisiqbal.weatherforecast.data.repository.WeatherForecastRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class WeatherForecastViewModel(
    private val repository: WeatherForecastRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    // main view states
    val weatherForecastResponse = MutableLiveData<WeatherForecastResponse>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()
    var query: String = ""

    fun setNewQuery(newQuery: String) {
        query = newQuery
    }

    fun getForecastData() {
        isError.value = false
        isLoading.value = true
        viewModelScope.launch(dispatcher) {
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
