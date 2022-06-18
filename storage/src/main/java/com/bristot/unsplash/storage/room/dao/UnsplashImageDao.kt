package com.bristot.unsplash.storage.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bristot.unsplash.storage.room.model.UnsplashImage
import com.bristot.unsplash.storage.room.table.UNSPLASH_IMAGE_TABLE

@Dao
interface UnsplashImageDao {

    @Query("SELECT * FROM $UNSPLASH_IMAGE_TABLE WHERE id =:id ")
    fun getById(id: String): UnsplashImage

    @Query("SELECT * FROM $UNSPLASH_IMAGE_TABLE")
    fun getAll(): PagingSource<Int, UnsplashImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: List<UnsplashImage>)

    @Query("DELETE FROM $UNSPLASH_IMAGE_TABLE")
    suspend fun clear()
}
