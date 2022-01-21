package com.example.kinofanplus.view

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.kinofanplus.model.database.MyMovieDao
import com.example.kinofanplus.model.database.MyMovieDataBase
import com.google.firebase.messaging.FirebaseMessaging

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this

        //генерация токена
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("MyFirebase", "token = ${task.result}")
            }
        }
    }

    companion object {
        private var appInstance: App? = null
        private var db: MyMovieDataBase? = null

        private const val DB_NAME = "MyMovies.db"

        fun getMyMovieDao(): MyMovieDao {
            if (db == null) {
                if (appInstance == null) throw IllegalStateException("Некорректный запуск приложения")

                db = Room.databaseBuilder(
                    appInstance!!,
                    MyMovieDataBase::class.java,
                    DB_NAME
                ).allowMainThreadQueries()
                    .build()
            }
            return db!!.myMovieDao()
        }
    }
}