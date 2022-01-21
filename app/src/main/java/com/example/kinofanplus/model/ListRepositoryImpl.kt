package com.example.kinofanplus.model

import com.example.kinofanplus.model.movie_list_gson.MovieDTO
import com.example.kinofanplus.model.movie_list_gson.getListMoviesFromServer

class ListRepositoryImpl : ListRepository {
    override fun getMovieFromServer(): List<MovieDTO> = getListMoviesFromServer()
    override fun getMovieFromLocalSource(): List<MovieDTO> = TODO("Метод локальной БД")
}