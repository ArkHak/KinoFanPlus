package com.example.kinofanplus.model

class RepositoryImpl : Repository {
    override fun getMovieFromServer(): List<Movie> = getLazyListMovies()
    override fun getMovieFromLocalSource(): List<Movie> = getLazyListMovies()
}