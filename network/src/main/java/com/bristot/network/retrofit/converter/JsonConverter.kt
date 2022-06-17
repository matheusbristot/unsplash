package com.bristot.network.retrofit.converter

import retrofit2.Converter

interface JsonConverter {
    fun getJsonConverterFactory(): Converter.Factory
}