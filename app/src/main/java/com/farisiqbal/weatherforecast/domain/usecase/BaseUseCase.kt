package com.farisiqbal.weatherforecast.domain.usecase

import com.farisiqbal.weatherforecast.data.api.ResultLoad
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by farisiqbal on 30/05/2020
 */
abstract class BaseUseCase {
    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): ResultLoad<T> {
        return withContext(dispatcher) {
            try {
                ResultLoad.Success(apiCall.invoke())
            } catch (error: Throwable) {
                when (error) {
                    is IOException -> ResultLoad.Error(ERROR_NO_NETWORK)
                    is HttpException -> ResultLoad.Error(error.message(), error.code())
                    else -> ResultLoad.Error(ERROR_DEFAULT)
                }
            }
        }
    }

    companion object {
        // error messages
        const val ERROR_DEFAULT = "Request did not succeed"
        const val ERROR_NO_NETWORK = "No network connection"
    }
}