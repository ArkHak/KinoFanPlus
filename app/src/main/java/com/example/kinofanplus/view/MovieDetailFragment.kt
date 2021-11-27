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
        fun newInstance(bundle: Bundle): MovieDetailFragment =
            MovieDetailFragment().apply { arguments = bundle }
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

//сразу проверяем на null, и если нет, то отрисовываем
        arguments?.getParcelable<Movie>(MOVIE_KEY)?.let { movie ->
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