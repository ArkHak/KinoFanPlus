package com.example.kinofanplus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinofanplus.model.*
import com.example.kinofanplus.model.database.MyMovieEntity
import com.example.kinofanplus.model.movie_list_gson.MovieDTO
import com.example.kinofanplus.view.App
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailVM : ViewModel() {
    private val repository: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource())
    private val localRepository: LocalRepository = LocalRepositoryImpl(App.getMyMovieDao())
    private val detailLiveData = MutableLiveData<AppStateGetMovieDetails>()

    val liveData: LiveData<AppStateGetMovieDetails> = detailLiveData

    fun getMovieFromRemoteSource(id: Long) {
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

    fun putLikeMovie(movieDTO: MovieDTO) {
        localRepository.putLikeMovie(
            MyMovieEntity(
                id = movieDTO.id,
                title = movieDTO.title,
                releaseDate = movieDTO.releaseDate,
                myReview = "",
                isLiked = true,
                isViewed = false
            )
        )
    }

    fun removeLikedMovie(id: Long) {
        localRepository.removeLikedMovie(id)
    }

    fun checkOnFavoriteMovie(id: Long): Boolean {
        return localRepository.getLikeMovieForId(id)
    }

    fun checkOnViewedMovie(id: Long): Boolean {
        return localRepository.getViewedMovieForId(id)
    }

    fun putViewedMovie(movieDetail: MovieDTO) {
        TODO("Not yet implemented")
    }

    fun removeViewedMovie(id: Long) {
        TODO("Not yet implemented")
    }


}