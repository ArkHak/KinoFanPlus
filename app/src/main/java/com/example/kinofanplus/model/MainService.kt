package com.example.kinofanplus.model

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kinofanplus.BuildConfig
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

const val MAIN_SERVICE_STRING_EXTRA = "MainServiceExtra"
const val ID_MOVIE = "IDMovie"
const val DETAILS_INTENT_FILTER = "DETAILS_INTENT_FILTER"
const val MOVIE_DETAIL_EXTRA = "MOVIE_DETAIL_EXTRA"
const val RESULT_EXTRA = "RESULT_EXTRA"
const val SUCCESS_RESULT = "SUCCESS_RESULT"


class MainService(name: String = "MainService") : IntentService(name) {

    override fun onHandleIntent(intent: Intent?) {

        Log.d("MainService", "onHandlerIntent ${intent?.getStringExtra(MAIN_SERVICE_STRING_EXTRA)}")

        intent?.let {
            val id = intent.getIntExtra(ID_MOVIE, 0)

            if (id == 0) {
                onEmptyData()
            } else {
                loadMovie(id)
            }
        } ?: onEmptyIntent()
    }

    private fun loadMovie(id: Int) {
        var urlConnection: HttpsURLConnection? = null

        val uri = try {
            URL("https://api.themoviedb.org/3/movie/$id?api_key=${BuildConfig.MOVIE_API_KEY}&language=ru-RU")
        } catch (e: MalformedURLException) {
            onMalformedURL()
            return
        }

        try {
            urlConnection = uri.openConnection() as HttpsURLConnection
            urlConnection.apply {
                requestMethod = "GET"
                readTimeout = 10000
            }

            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val result = reader.lines().collect(Collectors.joining("\n"))

            val movieDTO: MovieDTO = Gson().fromJson(result, MovieDTO::class.java)
//если пришёл movie
            onResponse(movieDTO)

        } catch (e: Exception) {
            onErrorResponse(e.message ?: "Unknown Error")
            Log.e("", "FAILED", e)
        } finally {
            urlConnection?.disconnect()
        }

    }

    private fun onResponse(movie: MovieDTO) {

        //отправка интента через LocalBroadcastManager
        LocalBroadcastManager.getInstance(this).sendBroadcast(
            Intent(DETAILS_INTENT_FILTER)
                .putExtra(RESULT_EXTRA, SUCCESS_RESULT)
                .putExtra(MOVIE_DETAIL_EXTRA, movie)
        )
    }

    //пришла испорченная ссылка
    private fun onMalformedURL() {
        TODO("Not yet implemented")
    }

    private fun onErrorResponse(s: String) {
        TODO("Not yet implemented")
    }

    private fun onEmptyIntent() {
        TODO("Not yet implemented")
    }

    private fun onEmptyData() {
        TODO("Not yet implemented")
    }
}
