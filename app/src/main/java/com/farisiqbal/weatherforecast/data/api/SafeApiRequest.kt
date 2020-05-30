package com.farisiqbal.weatherforecast.data.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by farisiqbal on 30/05/2020
 */
interface SafeApiRequest {
    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): ApiResult<T> {
        return withContext(dispatcher) {
            try {
                ApiResult.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> {
                        ApiResult.Error(ApiService.ERROR_NO_NETWORK)
                    }
                    is HttpException -> {
                        ApiResult.Error(throwable.message(), throwable.code())
                    }
                    else -> {
                        ApiResult.Error(ApiService.ERROR_DEFAULT)
                    }
                }
            }
        }
    }
}