package com.example.kinofanplus.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kinofanplus.R
import com.example.kinofanplus.databinding.ItemMovieListBinding
import com.example.kinofanplus.model.database.MyMovieEntity

class MovieFavoriteListAdapter : RecyclerView.Adapter<MovieFavoriteListAdapter.MovieHolder>() {
    var movieList: List<MyMovieEntity> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listenerFavoriteClick: OnMovieClickListener? = null

    inner class MovieHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemMovieListBinding.bind(item)
        fun bind(movie: MyMovieEntity) {
            with(binding) {
                movieTitle.text = movie.title
                movieReleaseDate.text = movie.releaseDate
                // TODO: 22.12.2021 Загрузка данных через интернет?
            }

            itemView.setOnClickListener {
                listenerFavoriteClick?.onMovieClick(movie)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieFavoriteListAdapter.MovieHolder =
        MovieHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        )

    override fun onBindViewHolder(holder: MovieFavoriteListAdapter.MovieHolder, position: Int) {
        holder.bind(movieList[position])
        Log.d("TAG", position.toString())
    }

    override fun getItemCount(): Int = movieList.size

    fun interface OnMovieClickListener {
        fun onMovieClick(movie: MyMovieEntity)
    }
}