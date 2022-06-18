package com.bristot.unsplash.storage.di

import com.bristot.unsplash.storage.UnsplashDatabase
import com.bristot.unsplash.storage.room.table.UNSPLASH_DATABASE
import com.bristot.unsplash.storage.room.util.buildRoom
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val storageModule = module {
    single { buildRoom<UnsplashDatabase>(androidApplication(), UNSPLASH_DATABASE) }
}
