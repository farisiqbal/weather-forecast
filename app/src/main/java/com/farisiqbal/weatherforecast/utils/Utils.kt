package com.farisiqbal.weatherforecast.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.farisiqbal.weatherforecast.data.api.response.Main
import com.farisiqbal.weatherforecast.data.api.response.Weather

// view extensions
fun ImageView.setImageUrl(imgUrl: String?) {
    if (imgUrl.isNullOrBlank()) return

    Glide.with(this)
        .load(imgUrl)
        .into(this)
}

fun ImageView.setWeatherIconUrl(iconCode: String?) =
    setImageUrl("https://openweathermap.org/img/wn/$iconCode@2x.png")

fun View.setVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

// data extensions
fun Main.getTemperatureRangeText(): String = "${tempMin.toDegree()}/${tempMax.toDegree()}"

fun Weather.getWeatherDateText(): String {
    return "" // todo : today, tomorrow, 27 Feb 2020
}

fun Double.toDegree(): String = "${this.toInt()}°"