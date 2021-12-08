package com.example.kinofanplus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinofanplus.BuildConfig
import com.example.kinofanplus.model.DetailsRepository
import com.example.kinofanplus.model.DetailsRepositoryImpl
import com.example.kinofanplus.model.RemoteDataSource
import com.example.kinofanplus.model.movie_list_gson.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailVM : ViewModel() {
    private val repository: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource())
    private val detailLiveData = MutableLiveData<AppStateGetMovieDetails>()

    val liveData: LiveData<AppStateGetMovieDetails> = detailLiveData

    fun getMovieFromRemoteSource(id: Int) {
        detailLiveData.value = AppStateGetMovieDetails.Loading

        val link =
            "https://api.themoviedb.org/3/movie/$id?api_key=${BuildConfig.MOVIE_API_KEY}&language=ru-RU"

        repository.getMovieDetailFromServer(id, object : Callback<Result> {

            override fun onFailure(call: Call<Result>, t: Throwable) {
                detailLiveData.postValue(AppStateGetMovieDetails.Error(t))
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                response.body()?.let {
                    detailLiveData.postValue(checkResponse(it))
                }
            }
        })
    }

    private fun checkResponse(response: Result): AppStateGetMovieDetails {
        return AppStateGetMovieDetails.Success(response)
    }
}