package com.example.kinofanplus.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.kinofanplus.R
import com.example.kinofanplus.databinding.FragmentMovieDetailBinding
import com.example.kinofanplus.model.movie_list_gson.MovieDTO
import com.example.kinofanplus.viewmodel.AppStateGetMovieDetails
import com.example.kinofanplus.viewmodel.MovieDetailVM

class MovieDetailFragment : Fragment() {

    companion object {
        const val MOVIE_KEY = "MOVIE_KEY"
    }

    private val viewModel: MovieDetailVM by lazy {
        ViewModelProvider(this).get(MovieDetailVM::class.java)
    }

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieDetail: MovieDTO

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

        arguments?.getLong(MOVIE_KEY)?.let { id ->
            viewModel.getMovieFromRemoteSource(id)
        }
    }

    private fun renderData(state: AppStateGetMovieDetails) {
        when (state) {
            is AppStateGetMovieDetails.Success -> {
                displayMovie(state.movie)
                displayRendersWidgets(state.movie.id)
                initWidgets()
            }
            is AppStateGetMovieDetails.Error -> {
                Toast.makeText(requireContext(), "Ошибка при загрузке фильма", Toast.LENGTH_LONG)
                    .show()
            }
        }

    }

    private fun initWidgets() {
        actionToggleButtonLikeMovie()
        actionToggleButtonViewedMovie()
        actionToggleButtonReviewedMovie()
    }

    private fun displayMovie(movie: MovieDTO) {
        with(binding) {
            movieDetail = movie
            titleMovie.text = movie.title
            originalTitleMovie.text = movie.originalTitle
            releaseDateMovie.text = movie.releaseDate
            voteAverageMovie.text = movie.voteAverage.toString()
            overviewMovie.text = movie.overview
            checkOnReviewForEmpty()

            moviePoster.load("$POSTER_BASE_URL${movie.posterPath}") {
                placeholder(R.drawable.placeholder2)
                error(R.drawable.tmp_no_poster)
            }
            backgroundPoster.load("$POSTER_BASE_URL${movie.posterPath}")
        }
    }

    private fun actionToggleButtonLikeMovie() {
        binding.isLiked.setOnClickListener {
            if (binding.isLiked.isChecked) {
                viewModel.putLikeMovie(movieDetail)
            } else {
                viewModel.removeLikedMovie(movieDetail.id)
            }
        }
    }

    private fun actionToggleButtonViewedMovie() {
        binding.isViewed.setOnClickListener {
            if (binding.isViewed.isChecked) {
                binding.isReview.visibility = View.VISIBLE
                viewModel.putViewedMovie(movieDetail)
            } else {
                binding.isReview.visibility = View.GONE
                viewModel.removeViewedMovie(movieDetail.id)
            }
        }
    }

    private fun actionToggleButtonReviewedMovie() {
        binding.isReview.setOnClickListener {
            if (binding.isViewed.isChecked) {
                val builder = AlertDialog.Builder(requireContext())
                val inflater = layoutInflater
                val dialogLayout = inflater.inflate(R.layout.reviewed_dialog, null)
                val reviewEditText = dialogLayout.findViewById<EditText>(R.id.review_edit_text)

                with(builder) {
                    setTitle(getString(R.string.header_dialog_reviewed))
                    setPositiveButton(getString(R.string.save_reviewed)) { dialog, which ->
                        viewModel.putReviewedOnMovie(movieDetail.id, reviewEditText.text.toString())
                        checkOnReviewForEmpty()
                    }
                    setNegativeButton(getString(R.string.cancel_reviewed)) { dialog, which ->
                        checkOnReviewForEmpty()
                    }
                    setView(dialogLayout)
                    show()
                }
            } else {

            }
        }
    }

    private fun displayRendersWidgets(id: Long) {
        binding.isLiked.isChecked = viewModel.checkOnFavoriteMovie(id)
        binding.isViewed.isChecked = viewModel.checkOnViewedMovie(id).also {
            binding.isReview.isChecked = viewModel.checkOnReviewedMovie(id)
            if (it) {
                binding.isReview.visibility = View.VISIBLE
            } else {
                binding.isReview.visibility = View.GONE
            }
        }
    }

    private fun checkOnReviewForEmpty() {
        binding.isReview.isChecked =
            viewModel.checkOnReviewedMovie(movieDetail.id).also {
                if (it) binding.reviewedMovie.text =
                    viewModel.getReviewedTextOnMovie(movieDetail.id)
                else binding.reviewedMovie.text = null
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}