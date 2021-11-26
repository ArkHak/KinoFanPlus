package com.example.kinofanplus.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kinofanplus.databinding.FragmentMovieDetailBinding
import com.example.kinofanplus.model.Movie

class MovieDetailFragment : Fragment() {

    companion object {
        const val MOVIE_KEY = "MOVIE_KEY"
        fun newInstance(bundle: Bundle): MovieDetailFragment {
            val fragment = MovieDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

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

        val movie = arguments?.getParcelable<Movie>(MOVIE_KEY)

        if (movie != null) {
            // TODO: 18.11.2021 Добавить отрисовку-вставку данных, ограничить RV(чтоб не выходило за границы)
            //TODO: Наполнить фрагмент
            with(binding) {
                titleMovie.text = movie.title
                releaseDateMovie.text = movie.releaseDate
                voteAverageMovie.text = movie.voteAverage.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}