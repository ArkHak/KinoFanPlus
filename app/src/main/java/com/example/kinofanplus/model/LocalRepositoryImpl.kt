package com.example.kinofanplus.model

import android.util.Log
import com.example.kinofanplus.model.database.MyMovieDao
import com.example.kinofanplus.model.database.MyMovieEntity

class LocalRepositoryImpl(private val dao: MyMovieDao) : LocalRepository {
    override fun getAllMyMovie(): List<MyMovieEntity> = dao.allMovieBD()

    override fun putLikeMovie(movie: MyMovieEntity) {
        dao.insertLikeMovie(movie)
    }

    override fun getLikeMovieForId(id: Long): Boolean {
        dao.checkingForMovie(id).let {
            return dao.getLikeMovieForId(id)
        }
    }

    override fun removeLikedMovie(id: Long) {
        dao.deleteLikeMovie(id)
    }

    override fun getViewedMovieForId(id: Long): Boolean {
        dao.checkingForMovie(id).let {
            return dao.getViewedMovieForId(id)
        }
    }

}