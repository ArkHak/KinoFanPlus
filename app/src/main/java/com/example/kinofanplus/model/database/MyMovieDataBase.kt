package com.example.kinofanplus.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MyMovieEntity::class], version = 1, exportSchema = true)
abstract class MyMovieDataBase : RoomDatabase() {

    abstract fun myMovieDao(): MyMovieDao
}