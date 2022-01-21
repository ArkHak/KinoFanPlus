package com.example.kinofanplus.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kinofanplus.R
import com.example.kinofanplus.databinding.FragmentFavoriteFilmListBinding
import com.example.kinofanplus.viewmodel.AppStateGetLikesMovieList
import com.example.kinofanplus.viewmodel.FilmListVM

class FavoriteFilmListFragment : Fragment() {

    private var _binding: FragmentFavoriteFilmListBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { MovieFavoriteListAdapter() }

    private val viewModel: FilmListVM by lazy {
        ViewModelProvider(this).get(FilmListVM::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteFilmListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.listenerFavoriteClick = MovieFavoriteListAdapter.OnMovieClickListener { movie ->
            Bundle().also { bundle ->
                bundle.putLong(MovieDetailFragment.MOVIE_KEY, movie.id)
                findNavController(requireParentFragment()).navigate(
                    R.id.action_navigation_favorites_list_films_to_movieDetailFragment,
                    bundle
                )
            }
        }

        binding.recyclerFavoriteMovieList.adapter = adapter
        viewModel.liveFavoriteData.observe(viewLifecycleOwner) { state ->
            renderData(state)
        }
        viewModel.getDataFromLocalSource()
    }

    private fun renderData(state: AppStateGetLikesMovieList?) {
        when (state) {
            is AppStateGetLikesMovieList.Success -> {
                adapter.movieList = state.movie
            }
            is AppStateGetLikesMovieList.Error -> Log.e("TAG", "Exception Load Local Data")
            else -> Log.e("TAG", "ELSE TAG Local Data")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}