package com.example.kinofanplus.model

interface Repository {
    fun getMovieFromServer(): List<Movie>
    fun getMovieFromLocalSource(): List<Movie>
}