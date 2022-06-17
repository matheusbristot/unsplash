package com.bristot.network.okhttp3

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

const val LOGGING_ID = "loggingInterceptor"

internal class LoggingInterceptor(private val isDebug: Boolean) {

    fun getLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}