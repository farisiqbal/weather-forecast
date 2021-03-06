package com.farisiqbal.weatherforecast.data.api.interceptor

import com.farisiqbal.weatherforecast.data.api.service.ServiceBuilder.APP_ID
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by farisiqbal on 30/05/2020
 */
class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter("appId", APP_ID)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}