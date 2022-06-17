package com.bristot.network.retrofit

import com.bristot.network.retrofit.converter.JsonConverter
import com.bristot.network.okhttp3.ClientSetup
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Retrofit

private const val BASE_URL = "https://api.unsplash.com/"

@ExperimentalSerializationApi
internal class RetrofitSetupImpl(
    private val setupClient: ClientSetup,
    private val converter: JsonConverter
) : RetrofitSetup {

    override fun getClient(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(setupClient())
        .addConverterFactory(converter.getJsonConverterFactory())
        .build()
}