package com.farisiqbal.weatherforecast.data.response


import com.google.gson.annotations.SerializedName

data class WeatherForecastResponse(
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val list: List<DailyWeatherData>,
    @SerializedName("message")
    val message: Int
)