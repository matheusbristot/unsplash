@file:OptIn(ExperimentalSerializationApi::class)

package com.bristot.network.di

import com.bristot.network.BuildConfig
import com.bristot.network.json.SetupJson
import com.bristot.network.json.SetupJsonImpl
import com.bristot.network.network_manager.NetworkManager
import com.bristot.network.network_manager.NetworkManagerImpl
import com.bristot.network.okhttp3.*
import com.bristot.network.retrofit.RetrofitSetupImpl
import com.bristot.network.retrofit.converter.JsonConverter
import com.bristot.network.retrofit.converter.JsonConverterImpl
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.Interceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {

    converter()
    interceptors()
    json()
    client()

    factory<NetworkManager> { NetworkManagerImpl(androidApplication()) }
}

private fun Module.json() {
    factory<SetupJson> { SetupJsonImpl() }
}

private fun Module.client() {
    factory {
        val timeout = 15L
        ClientSetup(
            timeout = timeout,
            loggingInterceptor = get(named(LOGGING_ID)),
            clientIdInterceptor = get(named(CLIENT_ID))
        )
    }
    single { RetrofitSetupImpl(setupClient = get(), converter = get()).getClient() }
}

private fun Module.converter() {
    factory<JsonConverter> { JsonConverterImpl(setupJson = get()) }
}

private fun Module.interceptors() {
    factory<Interceptor>(named(CLIENT_ID)) {
        ClientIdInterceptor(BuildConfig.API_KEY)
    }

    factory(named(LOGGING_ID)) {
        LoggingInterceptor(isDebug = BuildConfig.DEBUG).getLoggingInterceptor()
    }
}
