package com.example.kinofanplus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinofanplus.BuildConfig
import com.example.kinofanplus.model.DetailsRepository
import com.example.kinofanplus.model.DetailsRepositoryImpl
import com.example.kinofanplus.model.RemoteDataSource
import com.example.kinofanplus.model.movie_list_gson.Result
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.text.ParseException

class MovieDetailVM : ViewModel() {
    private val repository: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource())
    private val detailLiveData = MutableLiveData<AppStateGetMovieDetails>()

    val liveData: LiveData<AppStateGetMovieDetails> = detailLiveData

    fun getMovieFromRemoteSource(id: Int) {
        detailLiveData.value = AppStateGetMovieDetails.Loading

        val link =
            "https://api.themoviedb.org/3/movie/$id?api_key=${BuildConfig.MOVIE_API_KEY}&language=ru-RU"

        repository.getMovieDetailFromServer(link, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                detailLiveData.postValue(AppStateGetMovieDetails.Error(e))
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { body ->
                    detailLiveData.postValue(checkResponse(body))
                }
            }
        })
    }

    private fun checkResponse(response: String): AppStateGetMovieDetails {
        val movieDTO = Gson().fromJson(response, Result::class.java)

        return if (movieDTO != null) {
            AppStateGetMovieDetails.Success(movieDTO)
        } else {
            AppStateGetMovieDetails.Error(ParseException("Не смог распарсить Т_Т", 0))
        }
    }
}