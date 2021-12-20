package com.example.kinofanplus.model.database

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE

@Dao
interface MyMovieDao {

    @Query("SELECT * FROM MyMovieEntity")
    fun allMovieBD(): List<MyMovieEntity>

    @Insert(onConflict = IGNORE)
    fun insertLikeMovie(movie: MyMovieEntity)

    @Query("DELETE FROM MyMovieEntity WHERE id = :id")
    fun deleteLikeMovie(id: Long)

    @Query("SELECT isLiked FROM MyMovieEntity WHERE id = :id")
    fun getLikeMovieForId(id: Long): Boolean

    @Query("SELECT isViewed FROM MyMovieEntity WHERE id = :id")
    fun getViewedMovieForId(id: Long): Boolean

    @Query("SELECT myReview FROM MyMovieEntity WHERE id = :id")
    fun getReviewByMovieID(id: Long): String

    @Query("SELECT * FROM MyMovieEntity WHERE id = :id")
    fun checkingForMovie(id: Long): MyMovieEntity

    @Insert(onConflict = IGNORE)
    fun insertReviewMovie(movie: MyMovieEntity)

    @Update
    fun updateReviewMovie(movie: MyMovieEntity)
}