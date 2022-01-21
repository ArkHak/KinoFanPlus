package com.example.kinofanplus.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyMovieEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val releaseDate: String,
    val myReview: String,
    val isLiked: Boolean,
    val isViewed: Boolean
)