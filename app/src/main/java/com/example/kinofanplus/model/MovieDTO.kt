package com.example.kinofanplus.model

import com.google.gson.annotations.SerializedName

data class MovieDTO(
    val id: Int?,
    val title: String?,
    val original_title: String?,
    val overview: String?,
    val release_date: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("poster_path")
    val posterPath: String,

    )

//TODO получить массив жанров