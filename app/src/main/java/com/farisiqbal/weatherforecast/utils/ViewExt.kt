package com.farisiqbal.weatherforecast.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by farisiqbal on 30/05/2020
 */
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