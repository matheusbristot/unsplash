package com.bristot.unsplash.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bristot.unsplash.storage.room.model.UnsplashRemoteKeys
import com.bristot.unsplash.storage.room.table.UNSPLASH_REMOTE_KEYS_TABLE

@Dao
interface UnsplashRemoteKeysDao {

    @Query("SELECT * FROM $UNSPLASH_REMOTE_KEYS_TABLE WHERE id =:id")
    suspend fun getRemoteKeys(id: String): UnsplashRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeys: List<UnsplashRemoteKeys>)

    @Query("DELETE FROM $UNSPLASH_REMOTE_KEYS_TABLE")
    suspend fun clear()

}
