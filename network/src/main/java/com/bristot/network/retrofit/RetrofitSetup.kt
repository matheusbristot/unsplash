package com.bristot.network.retrofit

import retrofit2.Retrofit

interface RetrofitSetup {
    fun getClient(): Retrofit
}
