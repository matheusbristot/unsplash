package com.bristot.network.okhttp3

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

internal class ClientSetup(
    private val timeout: Long,
    private val loggingInterceptor: Interceptor,
    private val clientIdInterceptor: Interceptor
) {

    operator fun invoke(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(clientIdInterceptor)
        .writeTimeout(timeout, TimeUnit.SECONDS)
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .readTimeout(timeout, TimeUnit.SECONDS)
        .build()
}