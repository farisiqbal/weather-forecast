package com.farisiqbal.weatherforecast.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecast
import kotlinx.coroutines.launch

class WeatherForecastViewModel : ViewModel() {

    // main view states
    val weatherForecasts = MutableLiveData<List<WeatherForecast>>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()
    private var query: String = ""

    fun setNewQuery(newQuery: String) {
        if (newQuery != query) {
            query = newQuery
        }
    }

    fun getForecastData() {
        isLoading.value = true
        viewModelScope.launch {
            // todo fetch  api
            isLoading.value = false
        }
    }
}
