package com.bristot.unsplash.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bristot.unsplash.storage.room.dao.UnsplashImageDao
import com.bristot.unsplash.storage.room.dao.UnsplashRemoteKeysDao
import com.bristot.unsplash.storage.room.model.UnsplashImage
import com.bristot.unsplash.storage.room.model.UnsplashRemoteKeys

@Database(
    entities = [UnsplashImage::class, UnsplashRemoteKeys::class],
    version = 1
)
abstract class UnsplashDatabase : RoomDatabase() {
    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao
}
