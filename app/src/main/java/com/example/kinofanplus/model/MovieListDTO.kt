package com.example.kinofanplus.model


import android.util.Log
import com.example.kinofanplus.BuildConfig
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

data class MovieListDTO(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

fun getListMovies(): MutableList<Result> {
    val dataList: MutableList<Result> = mutableListOf()


    val uri =
        URL("https://api.themoviedb.org/3/movie/now_playing?api_key=${BuildConfig.MOVIE_API_KEY}&language=ru-RU&page=1")
    var urlConnection: HttpsURLConnection? = null

    try {
        urlConnection = uri.openConnection() as HttpsURLConnection
        urlConnection.apply {
            requestMethod = "GET"
            readTimeout = 10000
        }

        val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
        val result = reader.lines().collect(Collectors.joining("\n"))

        val movieListDTO: MovieListDTO = Gson().fromJson(result, MovieListDTO::class.java)
        dataList.addAll(movieListDTO.results)
    } catch (e: Exception) {
        Log.e("", "FAILED", e)
    } finally {
        urlConnection?.disconnect()
    }

    return dataList
}