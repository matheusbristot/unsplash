package com.bristot.network.retrofit.converter

import com.bristot.network.json.SetupJson
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter

private const val CONTENT_TYPE = "application/json"

@ExperimentalSerializationApi
internal class JsonConverterImpl(private val setupJson: SetupJson) : JsonConverter {

    private val type: MediaType = CONTENT_TYPE.toMediaType()

    override fun getJsonConverterFactory(): Converter.Factory {
        return setupJson.getJson().asConverterFactory(type)
    }

}