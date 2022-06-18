package com.bristot.unsplash.storage.room.util

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

internal inline fun <reified DATABASE : RoomDatabase> buildRoom(
    application: Application,
    name: String
) = Room.databaseBuilder(application, DATABASE::class.java, name).build()
