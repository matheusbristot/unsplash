package com.bristot.unsplash

import android.app.Application
import com.bristot.network.di.networkModule
import com.bristot.unsplash.storage.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class UnsplashApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@UnsplashApplication)
            modules(networkModule, storageModule)
        }
    }
}