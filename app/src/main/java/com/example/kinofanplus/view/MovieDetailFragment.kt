package com.example.kinofanplus.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.kinofanplus.R
import com.example.kinofanplus.databinding.FragmentMovieDetailBinding
import com.example.kinofanplus.model.movie_list_gson.Result
import com.example.kinofanplus.viewmodel.AppStateGetMovieDetails
import com.example.kinofanplus.viewmodel.MovieDetailVM

class MovieDetailFragment : Fragment() {

    companion object {
        const val MOVIE_KEY = "MOVIE_KEY"
        fun newInstance(bundle: Bundle): MovieDetailFragment =
            MovieDetailFragment().apply { arguments = bundle }
    }

    private val viewModel: MovieDetailVM by lazy {
        ViewModelProvider(this).get(MovieDetailVM::class.java)
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

        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            renderData(state)
        }

        arguments?.getInt(MOVIE_KEY)?.let { id ->
            viewModel.getMovieFromRemoteSource(id)
        }
    }

    private fun renderData(state: AppStateGetMovieDetails) {
        when (state) {
            is AppStateGetMovieDetails.Success -> {
                displayMovie(state.movie)
            }
            is AppStateGetMovieDetails.Error -> {
                Toast.makeText(requireContext(), "Ошибка при загрузке фильма", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun displayMovie(movie: Result) {
        with(binding) {
            titleMovie.text = movie.title
            originalTitleMovie.text = movie.originalTitle
            releaseDateMovie.text = movie.releaseDate
            voteAverageMovie.text = movie.voteAverage.toString()
            overviewMovie.text = movie.overview

            //TODO placeholder
            moviePoster.load("$POSTER_BASE_URL${movie.posterPath}"){
                placeholder(R.drawable.placeholder2)
                error(R.drawable.tmp_no_poster)
            }
            backgroundPoster.load("$POSTER_BASE_URL${movie.posterPath}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}