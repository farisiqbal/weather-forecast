package com.farisiqbal.weatherforecast.presentation.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farisiqbal.weatherforecast.data.api.ResultLoad
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
import com.farisiqbal.weatherforecast.data.repository.WeatherForecastRepository
import com.farisiqbal.weatherforecast.domain.usecase.GetWeatherForecastDataUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class WeatherForecastViewModel(
    private val useCase: GetWeatherForecastDataUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    // main view states
    private val _weatherDataResult = MutableLiveData<ResultLoad<WeatherForecastResponse>>()
    val weatherDataResult: LiveData<ResultLoad<WeatherForecastResponse>> get() = _weatherDataResult

    val weatherForecastResponse = MutableLiveData<WeatherForecastResponse>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()

    var query: String = ""
        private set

    init {
        fetchForecastData()
    }

    fun setNewQuery(newQuery: String) {
        query = newQuery
    }

    fun getForecastData() {
        isError.value = false
        isLoading.value = true
        viewModelScope.launch(dispatcher) {
            val result = useCase.invoke(query)
            if (result is ResultLoad.Success) {
                weatherForecastResponse.value = result.data
            } else {
                isError.value = true
            }
            isLoading.value = false
        }
    }

    fun fetchForecastData() = viewModelScope.launch(dispatcher) {
        _weatherDataResult.postValue(ResultLoad.Loading)
        _weatherDataResult.postValue(useCase.invoke(query))
    }
}
