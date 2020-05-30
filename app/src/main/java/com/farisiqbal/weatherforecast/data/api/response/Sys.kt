package com.farisiqbal.weatherforecast.data.api.response


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("pod")
    val pod: String
)