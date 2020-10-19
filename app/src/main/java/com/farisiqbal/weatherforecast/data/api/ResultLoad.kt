package com.farisiqbal.weatherforecast.data.api

/**
 * Custom class for handling the result of api call
 */
sealed class ResultLoad<out T> {
    data class Success<out T>(val data: T) : ResultLoad<T>()
    data class Error(val message: String?, val code: Int = -1) : ResultLoad<Nothing>()
    object Loading: ResultLoad<Nothing>()

    /**
     * Peeking the data within this Result class.
     * Only returns the data when the Result is Success.
     */
    val peekData: T?
        get() = when (this) {
            is Success -> data
            is Error -> null
            is Loading -> null
        }

}