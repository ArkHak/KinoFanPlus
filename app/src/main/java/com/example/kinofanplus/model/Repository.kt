package com.example.kinofanplus.model

interface Repository {
    fun getMovieFromServer(): List<Result>
    fun getMovieFromLocalSource(): List<Movie>
}