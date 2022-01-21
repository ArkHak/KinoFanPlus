package com.example.kinofanplus.model

import com.example.kinofanplus.model.movie_list_gson.MovieDTO

interface ListRepository {
    fun getMovieFromServer(): List<MovieDTO>
    fun getMovieFromLocalSource(): List<MovieDTO>
}