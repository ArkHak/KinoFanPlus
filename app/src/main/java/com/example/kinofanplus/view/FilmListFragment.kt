package com.example.kinofanplus.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.kinofanplus.R
import com.example.kinofanplus.databinding.FragmentFilmListBinding
import com.example.kinofanplus.viewmodel.AppStateGetMovieList
import com.example.kinofanplus.viewmodel.FilmListVM

class FilmListFragment : Fragment() {

    private var _binding: FragmentFilmListBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { MovieListAdapter() }

    private val viewModel: FilmListVM by lazy {
        ViewModelProvider(this).get(FilmListVM::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.listenerClick = MovieListAdapter.OnMovieClickListener { movie ->
            Bundle().also { bundle ->
                bundle.putInt(MovieDetailFragment.MOVIE_KEY, movie.id)
                findNavController(requireParentFragment()).navigate(
                    R.id.action_navigation_list_films_to_movieDetailFragment,
                    bundle
                )
            }

        }

        binding.recyclerViewMovieList.adapter = adapter
        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            renderData(state)
        }

        viewModel.getMovieFromServerSource()
    }

    private fun renderData(state: AppStateGetMovieList?) {
        when (state) {
            is AppStateGetMovieList.Success -> {
                adapter.movieList = state.movie
            }
            is AppStateGetMovieList.Error -> Log.e("TAG", "Exception Load")

            else -> {
                Log.e("TAG", "ELSE TAG")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
