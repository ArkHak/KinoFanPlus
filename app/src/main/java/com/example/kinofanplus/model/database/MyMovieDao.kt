package com.example.kinofanplus.model.database

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE

@Dao
interface MyMovieDao {

    @Query("SELECT * FROM MyMovieEntity")
    fun allMovieBD(): List<MyMovieEntity>

    @Query("DELETE FROM MyMovieEntity WHERE id = :id")
    fun deleteMovie(id: Long)

    @Query("SELECT * FROM MyMovieEntity WHERE id = :id")
    fun checkingMovieForBD(id: Long): MyMovieEntity


    @Insert(onConflict = IGNORE)
    fun insertLikeMovie(movie: MyMovieEntity)

    @Query("SELECT isLiked FROM MyMovieEntity WHERE id = :id")
    fun getLikeMovieForId(id: Long): Boolean

    @Query("UPDATE MyMovieEntity SET isLiked = :value WHERE id = :id")
    fun updateMovieOnLike(id: Long, value: Boolean)


    @Insert(onConflict = IGNORE)
    fun insertViewedMovie(movie: MyMovieEntity)

    @Query("SELECT isViewed FROM MyMovieEntity WHERE id = :id")
    fun getViewedMovieForId(id: Long): Boolean

    @Query("UPDATE MyMovieEntity SET isViewed = :value WHERE id = :id")
    fun updateMovieOnViewed(id: Long, value: Boolean)


    @Query("SELECT myReview FROM MyMovieEntity WHERE id = :id")
    fun getReviewByMovieID(id: Long): String

    @Insert(onConflict = IGNORE)
    fun insertReviewMovie(movie: MyMovieEntity)

    @Update
    fun updateReviewMovie(movie: MyMovieEntity)
}