package com.example.kinofanplus.model

import com.example.kinofanplus.model.movie_list_gson.Result
import retrofit2.Callback


interface DetailsRepository {
    fun getMovieDetailFromServer(requestID: Int, callback: Callback<Result>)
}