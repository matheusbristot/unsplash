package com.bristot.network.json

import kotlinx.serialization.json.Json

internal class SetupJsonImpl : SetupJson {

    override fun getJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
}