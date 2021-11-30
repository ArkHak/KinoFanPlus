package com.example.kinofanplus.model

class RepositoryImpl : Repository {
    override fun getMovieFromServer(): List<Result> = getListMovies()
    override fun getMovieFromLocalSource(): List<Movie> = getLazyListMovies()
}