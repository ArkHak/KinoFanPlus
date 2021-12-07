package com.example.kinofanplus.model

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

class RemoteDataSource {

    fun getMovieDetail(link: String, callback: Callback) {

        val client = OkHttpClient()

        val request = Request.Builder()
            .url(link)
            .get()
            .build()

        val response = client.newCall(request).enqueue(callback)
    }
}