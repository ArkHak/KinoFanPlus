package com.example.kinofanplus.viewmodel

import com.example.kinofanplus.model.movie_list_gson.Result

sealed class AppStateGetMovieList {
    data class Success(val movie: List<Result>) : AppStateGetMovieList()
    data class Error(val error: Throwable) : AppStateGetMovieList()
    object Loading : AppStateGetMovieList()
}