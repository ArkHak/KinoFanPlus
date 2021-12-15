package com.example.kinofanplus.model

import com.example.kinofanplus.model.movie_list_gson.MovieDTO
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RemoteDataSource {

    private val movieApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        //мощная вещь для логирования запросов
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        )
        .build()
        .create(MovieApi::class.java)


    fun getMovieDetail(id: Long, callback: Callback<MovieDTO>) {
        movieApi.getMovieDetail(id).enqueue(callback)
    }
}