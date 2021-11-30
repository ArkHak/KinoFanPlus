package com.example.kinofanplus.model

import com.example.kinofanplus.model.movie_list_gson.Result

interface Repository {
    fun getMovieFromServer(): List<Result>
    fun getMovieFromLocalSource(): List<Movie>
}