package com.example.kinofanplus.model

import retrofit2.Callback
import com.example.kinofanplus.model.movie_list_gson.MovieDTO


class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DetailsRepository {

    override fun getMovieDetailFromServer(requestID: Int, callback: Callback<MovieDTO>) {
        remoteDataSource.getMovieDetail(requestID, callback)
    }
}