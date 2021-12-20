package com.example.kinofanplus.model

import com.example.kinofanplus.model.database.MyMovieEntity
import com.example.kinofanplus.model.movie_list_gson.MovieDTO

interface LocalRepository {
    fun getAllMyMovie(): List<MyMovieEntity>

    fun putLikeMovie(movie: MyMovieEntity)
    fun getLikeMovieForId(id: Long): Boolean
    fun removeLikedMovie(id: Long)
    fun getViewedMovieForId(id: Long): Boolean
}