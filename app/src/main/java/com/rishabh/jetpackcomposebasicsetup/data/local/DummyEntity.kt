package com.rishabh.jetpackcomposebasicsetup.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DummyEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val firstBrewed: String,
    val imageUrl: String?
)