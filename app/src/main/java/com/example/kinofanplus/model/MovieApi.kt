package com.example.kinofanplus.model

import com.example.kinofanplus.BuildConfig
import com.example.kinofanplus.model.movie_list_gson.MovieDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("3/movie/{id}")
    fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.MOVIE_API_KEY,
        @Query("language") language: String = "ru-RU"
    ): Call<MovieDTO>
}