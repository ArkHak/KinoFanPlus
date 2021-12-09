package com.example.kinofanplus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinofanplus.model.DetailsRepository
import com.example.kinofanplus.model.DetailsRepositoryImpl
import com.example.kinofanplus.model.RemoteDataSource
import com.example.kinofanplus.model.movie_list_gson.MovieDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailVM : ViewModel() {
    private val repository: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource())
    private val detailLiveData = MutableLiveData<AppStateGetMovieDetails>()

    val liveData: LiveData<AppStateGetMovieDetails> = detailLiveData

    fun getMovieFromRemoteSource(id: Int) {
        detailLiveData.value = AppStateGetMovieDetails.Loading

        repository.getMovieDetailFromServer(id, object : Callback<MovieDTO> {

            override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
                detailLiveData.postValue(AppStateGetMovieDetails.Error(t))
            }

            override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
                response.body()?.let {
                    detailLiveData.postValue(checkResponse(it))
                }
            }
        })
    }

    private fun checkResponse(response: MovieDTO): AppStateGetMovieDetails {
        return AppStateGetMovieDetails.Success(response)
    }
}