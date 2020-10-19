package com.farisiqbal.weatherforecast.presentation.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farisiqbal.weatherforecast.data.api.ResultLoad
import com.farisiqbal.weatherforecast.data.api.response.WeatherForecastResponse
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

    var query: String = ""
        private set

    init {
        getForecastData()
    }

    fun setNewQuery(newQuery: String) {
        query = newQuery
    }

    fun getForecastData() = viewModelScope.launch(dispatcher) {
        _weatherDataResult.postValue(ResultLoad.Loading)
        _weatherDataResult.postValue(useCase.invoke(query))
    }
}
