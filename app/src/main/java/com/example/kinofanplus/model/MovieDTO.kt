package com.example.kinofanplus.model

data class MovieDTO(
    val id: Int?,
    val title: String?,
    val original_title: String?,
    val overview: String?,
    val release_date: String?,
    val vote_average: Double?,
)

//TODO получить массив жанров