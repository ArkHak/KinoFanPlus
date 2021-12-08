package com.example.kinofanplus.model

import com.example.kinofanplus.model.movie_list_gson.Result
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RemoteDataSource {

    private val movieApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build().create(MovieApi::class.java)


    fun getMovieDetail(id: Int, callback: Callback<Result>) {

//        val client = OkHttpClient()
//
//        val request = Request.Builder()
//            .url(link)
//            .get()
//            .build()
//
//        client.newCall(request).enqueue(callback)

        movieApi.getMovieDetail(id).enqueue(callback)
    }
}