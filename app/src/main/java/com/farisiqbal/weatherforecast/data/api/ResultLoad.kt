package com.farisiqbal.weatherforecast.data.api

/**
 * Custom class for handling the result of api call
 */
sealed class ResultLoad<out T> {
    data class Success<out T>(val data: T) : ResultLoad<T>()
    data class Error(val message: String?, val code: Int = -1) : ResultLoad<Nothing>()
    object Loading: ResultLoad<Nothing>()
}