package com.example.kinofanplus.model

import okhttp3.Callback

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DetailsRepository {

    override fun getMovieDetailFromServer(requestList: String, callback: Callback) {
//requestID????
        remoteDataSource.getMovieDetail(requestList, callback)
    }
}