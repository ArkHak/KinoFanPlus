package com.example.kinofanplus.model

import android.util.Log
import com.example.kinofanplus.BuildConfig
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MovieLoader(
    private val idMovie: Int,
    private val listener: MovieLoaderListener
) {

    fun goToInternet() {

        CoroutineScope(Dispatchers.IO).launch {
            val uri =
                URL("https://api.themoviedb.org/3/movie/$idMovie?api_key=${BuildConfig.MOVIE_API_KEY}&language=ru-RU")
            var urlConnection: HttpsURLConnection? = null

            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.apply {
                    requestMethod = "GET"
                    readTimeout = 10000
                }

                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val result = reader.lines().collect(Collectors.joining("\n"))

                val movieDTO: MovieDTO = Gson().fromJson(result, MovieDTO::class.java)

                listener.onLoaded(movieDTO)

            } catch (e: Exception) {
                listener.onFailed(e)
                Log.e("", "FAILED", e)
            } finally {
                urlConnection?.disconnect()
            }
        }

    }


    interface MovieLoaderListener {
        fun onLoaded(movieDTO: MovieDTO)
        fun onFailed(throwable: Throwable)
    }
}