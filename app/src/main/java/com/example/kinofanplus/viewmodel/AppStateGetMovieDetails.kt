package com.example.kinofanplus.viewmodel

import com.example.kinofanplus.model.movie_list_gson.MovieDTO

sealed class AppStateGetMovieDetails {
    data class Success(val movie: MovieDTO) : AppStateGetMovieDetails()
    data class Error(val error: Throwable) : AppStateGetMovieDetails()
    object Loading : AppStateGetMovieDetails()
}
