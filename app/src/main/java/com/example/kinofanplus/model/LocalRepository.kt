package com.example.kinofanplus.model

import com.example.kinofanplus.model.database.MyMovieEntity
import com.example.kinofanplus.model.movie_list_gson.MovieDTO

interface LocalRepository {
    fun getAllMyMovie(): List<MyMovieEntity>

    fun putLikeMovie(movie: MyMovieEntity)
    fun removeLikedMovie(id: Long)
    fun getLikeMovieForId(id: Long): Boolean

    fun putViewedMovie(movie: MyMovieEntity)
    fun removeViewedMovie(id: Long)
    fun getViewedMovieForId(id: Long): Boolean

    fun getReviewedMovieForId(id: Long): Boolean
    fun putReviewedOnMovie(id: Long, reviewed: String)
    fun getReviewedTextOnMovie(id: Long): String
}