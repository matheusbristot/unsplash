package com.bristot.unsplash.storage.room.model

import androidx.room.Embedded

data class User(@Embedded val userLinks: UserLinks, val username: String)
