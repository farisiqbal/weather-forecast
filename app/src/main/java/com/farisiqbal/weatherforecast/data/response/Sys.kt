package com.farisiqbal.weatherforecast.data.response


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("pod")
    val pod: String
)