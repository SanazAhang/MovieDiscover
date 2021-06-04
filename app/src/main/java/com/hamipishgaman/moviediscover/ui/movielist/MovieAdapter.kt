package com.hamipishgaman.moviediscover.ui.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hamipishgaman.moviediscover.databinding.ItemMovieCardBinding
import com.hamipishgaman.moviediscover.domain.model.Model

class MovieAdapter(val clickListener: MovieListener) :
    ListAdapter<Model.Movie, MovieAdapter.MovieViewHolder>(DiffCallback()) {

    class MovieViewHolder(private val binding: ItemMovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Model.Movie, clickListener: MovieListener) = with(itemView) {
            binding.movie = movie
            binding.clickListener = clickListener
            setOnClickListener {
                // TODO: Handle on click
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position),clickListener)
    }
}

class MovieListener(val clickListener: (movie: Model.Movie) -> Unit) {
    fun onClick(movie: Model.Movie) = clickListener(movie)
}

class DiffCallback : DiffUtil.ItemCallback<Model.Movie>() {
    override fun areItemsTheSame(oldItem: Model.Movie, newItem: Model.Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Model.Movie, newItem: Model.Movie): Boolean {
        return oldItem == newItem
    }

}