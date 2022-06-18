package com.bristot.unsplash.storage

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bristot.unsplash.storage.room.model.UnsplashImage
import com.bristot.unsplash.storage.room.model.Urls
import com.bristot.unsplash.storage.room.model.User
import com.bristot.unsplash.storage.room.model.UserLinks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class TestUnsplashDataBase {

    private lateinit var db: UnsplashDatabase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, UnsplashDatabase::class.java
        ).setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor()).build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeUnsplashImageAndReadInList() = runTest(testDispatcher) {
        val images: List<UnsplashImage> = listOf(
            createUnsplashImage(id = "378217", likes = 1),
            createUnsplashImage(id = "287123", likes = 25, userName = "User Test")
        )

        db.unsplashImageDao().insertAll(images)


        with(images.first().id) {
            val result = db.unsplashImageDao().getById(this)
            assert(result.id == "378217")
            assert(result.user.username == "Unsplash Test")
            assert(result.urls.regular == "www.teste.com")
            assert(result.likes == 1)
        }
        with(images.last().id) {
            val result = db.unsplashImageDao().getById(this)
            assert(result.id == "287123")
            assert(result.likes == 25)
            assert(result.user.username == "User Test")
        }
    }

    @Test
    fun writeUnsplashImageAndClear() = runTest(testDispatcher) {
        val images: List<UnsplashImage> = listOf(
            createUnsplashImage(id = "378217", likes = 1),
            createUnsplashImage(id = "287123", likes = 25, userName = "User Test")
        )
        db.unsplashImageDao().insertAll(images)

        db.unsplashImageDao().clear()

        assert(db.unsplashImageDao().getById("378217") == null)
        assert(db.unsplashImageDao().getById("287123") == null)
    }

    private fun createUnsplashImage(
        id: String = String(),
        userName: String = "Unsplash Test",
        regular: String = "www.teste.com",
        html: String = "<b>Teste</b>",
        likes: Int = 0
    ) = UnsplashImage(
        id = id,
        urls = Urls(regular = regular),
        likes = likes,
        user = User(userLinks = UserLinks(html = html), username = userName)
    )
}
