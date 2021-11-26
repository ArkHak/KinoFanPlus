package com.example.kinofanplus.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int = 0,
    val title: String = "***",
    val posterPath: String = "***",
    val releaseDate: String = "***",
    val voteAverage: Double = 0.0
) : Parcelable

fun getLazyListMovies(): List<Movie> = listOf(
    Movie(1, "Король", "...", "19-19-2001", 9.7),
    Movie(1, "Приключение Тома Соера", "...", "19-19-2001", 9.7),
    Movie(1, "Как достать соседа", "...", "19-19-2001", 9.7),
    Movie(1, "НАчало", "...", "19-19-2001", 9.7),
    Movie(1, "Бойцовский клуб", "...", "19-19-2001", 9.7),
    Movie(1, "Возвращение мушкнитёров", "...", "19-19-2001", 9.7),
    Movie(1, "Король", "...", "19-19-2001", 9.7),
    Movie(1, "Приключение Тома Соера", "...", "19-19-2001", 9.7),
    Movie(1, "Как достать соседа", "...", "19-19-2001", 9.7),
    Movie(1, "Начало", "...", "19-19-2001", 9.7),
    Movie(1, "Бойцовский клуб", "...", "19-19-2001", 9.7),
    Movie(1, "Возвращение мушкнитёров", "...", "19-19-2001", 9.7),
)