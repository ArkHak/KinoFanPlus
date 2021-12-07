package com.example.kinofanplus.viewmodel

import com.example.kinofanplus.model.movie_list_gson.Result

sealed class AppStateGetMovieDetails {
    data class Success(val movie: Result) : AppStateGetMovieDetails()
    data class Error(val error: Throwable) : AppStateGetMovieDetails()
    object Loading : AppStateGetMovieDetails()
}
