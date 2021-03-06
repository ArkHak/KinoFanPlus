package com.example.kinofanplus.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kinofanplus.R
import com.example.kinofanplus.databinding.ItemMovieListBinding
import com.example.kinofanplus.model.movie_list_gson.MovieDTO
import com.squareup.picasso.Picasso

const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500/"

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {
    var movieList: List<MovieDTO> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listenerClick: OnMovieClickListener? = null

    inner class MovieHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemMovieListBinding.bind(item)
        fun bind(movie: MovieDTO) {
            with(binding) {
                movieTitle.text = movie.title
                movieReleaseDate.text = movie.releaseDate
                movieVoteAverage.text = movie.voteAverage.toString()
            }

            Picasso.get()
                .load("$POSTER_BASE_URL${movie.posterPath}")
                .placeholder(R.drawable.placeholder2)
                .error(R.drawable.tmp_no_poster)
                .into(binding.moviePoster)
            itemView.setOnClickListener {
                listenerClick?.onMovieClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder =
        MovieHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        )

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movieList[position])
        Log.d("TAG", position.toString())
    }

    override fun getItemCount(): Int = movieList.size

    fun interface OnMovieClickListener {
        fun onMovieClick(movie: MovieDTO)
    }
}