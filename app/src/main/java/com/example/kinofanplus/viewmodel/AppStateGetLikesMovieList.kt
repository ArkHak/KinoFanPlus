package com.example.kinofanplus.viewmodel

import com.example.kinofanplus.model.database.MyMovieEntity

sealed class AppStateGetLikesMovieList {
    data class Success(val movie: List<MyMovieEntity>) : AppStateGetLikesMovieList()
    data class Error(val error: Throwable) : AppStateGetLikesMovieList()
    object Loading : AppStateGetLikesMovieList()
}