package com.example.kinofanplus.model

import com.example.kinofanplus.model.database.MyMovieDao
import com.example.kinofanplus.model.database.MyMovieEntity

class LocalRepositoryImpl(private val dao: MyMovieDao) : LocalRepository {
    override fun getAllMyMovie(): List<MyMovieEntity> = dao.allMovieBD()

    override fun putLikeMovie(movie: MyMovieEntity) {
        dao.checkingMovieForBD(movie.id)?.let { it ->
            dao.updateMovieOnLike(it.id, true)
        } ?: dao.insertLikeMovie(movie)
    }

    override fun removeLikedMovie(id: Long) {
        if (dao.getViewedMovieForId(id)) {
            dao.updateMovieOnLike(id, false)
        } else {
            dao.deleteMovie(id)
        }
    }

    override fun getLikeMovieForId(id: Long): Boolean {
        dao.checkingMovieForBD(id)?.let {
            return dao.getLikeMovieForId(id)
        }
    }


    override fun putViewedMovie(movie: MyMovieEntity) {
        dao.checkingMovieForBD(movie.id)?.let { it ->
            dao.updateMovieOnViewed(it.id, true)
        } ?: dao.insertViewedMovie(movie)
    }

    override fun removeViewedMovie(id: Long) {
        if (dao.getLikeMovieForId(id)) {
            dao.updateMovieOnViewed(id, false)
        } else {
            dao.deleteMovie(id)
        }
    }

    override fun getViewedMovieForId(id: Long): Boolean {
        dao.checkingMovieForBD(id)?.let {
            return dao.getViewedMovieForId(id)
        }
    }


    override fun getReviewedMovieForId(id: Long): Boolean {
        dao.checkingMovieForBD(id)?.let {
            return dao.getReviewedMovieForId(id).isNotBlank()
        }
    }

    override fun putReviewedOnMovie(id: Long, reviewed: String) {
        dao.putReviewedMovieForId(id, reviewed)
    }

    override fun getReviewedTextOnMovie(id: Long): String {
        return dao.getReviewedMovieForId(id)
    }

}