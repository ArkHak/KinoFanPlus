package com.example.kinofanplus.model

import com.example.kinofanplus.model.movie_list_gson.Result
import com.example.kinofanplus.model.movie_list_gson.getListMovies

class RepositoryImpl : Repository {
    override fun getMovieFromServer(): List<Result> = getListMovies()
    override fun getMovieFromLocalSource(): List<Movie> = getLazyListMovies()
}