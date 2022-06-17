package com.bristot.network.okhttp3

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

const val CLIENT_ID = "clientIdInterceptor"
private const val HEADER_ID = "Authorization: Client-ID"

internal class ClientIdInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request().newBuilder().addHeader(HEADER_ID, apiKey).build()
        return chain.proceed(request)
    }
}