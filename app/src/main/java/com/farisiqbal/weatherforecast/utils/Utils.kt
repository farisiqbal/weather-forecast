package com.farisiqbal.weatherforecast.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by farisiqbal on 30/05/2020
 */
fun ImageView.setImageUrl(imgUrl: String?) {
    if (imgUrl.isNullOrBlank()) return

    Glide.with(context)
        .load(imgUrl)
        .fitCenter()
        .into(this)
}

fun Double.toDegree(): String {
    return "$this°"
}