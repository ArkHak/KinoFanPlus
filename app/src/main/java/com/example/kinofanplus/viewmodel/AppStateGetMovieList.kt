package com.example.kinofanplus.viewmodel

import com.example.kinofanplus.model.movie_list_gson.MovieDTO

sealed class AppStateGetMovieList {
    data class Success(val movie: List<MovieDTO>) : AppStateGetMovieList()
    data class Error(val error: Throwable) : AppStateGetMovieList()
    object Loading : AppStateGetMovieList()
}