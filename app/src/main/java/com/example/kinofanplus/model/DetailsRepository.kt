package com.example.kinofanplus.model

import okhttp3.Callback

interface DetailsRepository {
    fun getMovieDetailFromServer(requestList: String, callback: Callback)
}