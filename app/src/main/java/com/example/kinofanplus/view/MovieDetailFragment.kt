package com.example.kinofanplus.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kinofanplus.R
import com.example.kinofanplus.databinding.FragmentMovieDetailBinding
import com.example.kinofanplus.model.*
import com.squareup.picasso.Picasso

class MovieDetailFragment : Fragment() {

    companion object {
        const val MOVIE_KEY = "MOVIE_KEY"
        fun newInstance(bundle: Bundle): MovieDetailFragment =
            MovieDetailFragment().apply { arguments = bundle }
    }

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    //получение отправленного интента
    private val localResultBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.getStringExtra(RESULT_EXTRA)) {
                SUCCESS_RESULT -> {
                    intent.getParcelableExtra<MovieDTO>(MOVIE_DETAIL_EXTRA)
                        ?.let { displayMovie(it) }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//регистрируем слушаттеля BroadcastManager на созданный интент по имени
        LocalBroadcastManager.getInstance(requireContext())
            .registerReceiver(localResultBroadcastReceiver, IntentFilter(DETAILS_INTENT_FILTER))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//сразу проверяем на null, и если нет, то отрисовываем
        arguments?.getInt(MOVIE_KEY)?.let { id ->
            getMovie(id)
        }
    }

    private fun getMovie(IDMovie: Int) {

        requireActivity().startService(
            Intent(requireContext(), MainService::class.java).apply {
                putExtra(ID_MOVIE, IDMovie)
            })
    }

    private fun displayMovie(movie: MovieDTO) {
        with(binding) {
            titleMovie.text = movie.title
            originalTitleMovie.text = movie.original_title
            releaseDateMovie.text = movie.release_date
            voteAverageMovie.text = movie.voteAverage.toString()
            overviewMovie.text = movie.overview
        }

        Picasso.get()
            .load("$POSTER_BASE_URL${movie.posterPath}")
            //.placeholder(R.drawable.user_placeholder)
            .error(R.drawable.tmp_no_poster)
            .into(binding.moviePoster)

        Picasso.get()
            .load("$POSTER_BASE_URL${movie.posterPath}")
            //.placeholder(R.drawable.user_placeholder)
            .error(R.drawable.tmp_no_poster)
            .into(binding.backgroundPoster)
    }

    override fun onDestroyView() {
        //отписка от слушателя
        LocalBroadcastManager.getInstance(requireContext())
            .unregisterReceiver(localResultBroadcastReceiver)

        super.onDestroyView()
        _binding = null
    }
}