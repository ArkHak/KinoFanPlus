package com.example.kinofanplus.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kinofanplus.databinding.FragmentMovieDetailBinding
import com.example.kinofanplus.model.Movie
import com.example.kinofanplus.model.MovieDTO
import com.example.kinofanplus.model.MovieLoader

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

            MovieLoader(movie.id, object : MovieLoader.MovieLoaderListener {
                override fun onLoaded(movieDTO: MovieDTO) {
                    requireActivity().runOnUiThread {
                        displayMovie(movieDTO)
                    }
                }

                override fun onFailed(throwable: Throwable) {
                    //TODO добавить reload(Snackbar)
                    requireActivity().runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            throwable.localizedMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }).goToInternet()
        }
    }

    private fun displayMovie(movie: MovieDTO) {
        with(binding) {
            titleMovie.text = movie.title
            originalTitleMovie.text = movie.original_title
            releaseDateMovie.text = movie.release_date
            voteAverageMovie.text = movie.vote_average.toString()
            overviewMovie.text = movie.overview
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}