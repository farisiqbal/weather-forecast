package com.farisiqbal.weatherforecast.data.api

/**
 * Custom class for handling the result of api call
 */
sealed class ApiResult<out T> {
    data class Success<out T>(val value: T) : ApiResult<T>()
    data class Error(val message: String?, val code: Int = -1) : ApiResult<Nothing>()
}