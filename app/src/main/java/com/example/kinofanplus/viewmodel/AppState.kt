package com.example.kinofanplus.viewmodel

import com.example.kinofanplus.model.Movie
import com.example.kinofanplus.model.Result

sealed class AppState {
    data class Success(val movie: List<Result>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}