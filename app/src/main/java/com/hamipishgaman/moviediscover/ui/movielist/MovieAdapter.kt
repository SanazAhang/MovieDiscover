package com.hamipishgaman.moviediscover.ui.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hamipishgaman.moviediscover.databinding.ItemMovieCardBinding
import com.hamipishgaman.moviediscover.domain.model.Model

class MovieAdapter:
    ListAdapter<Model.Movie, MovieAdapter.MovieViewHolder>(DiffCallback()) {
    private lateinit var listener: ClickListener

     inner class MovieViewHolder(private val binding: ItemMovieCardBinding) :
        RecyclerView.ViewHolder(binding.root),View.OnClickListener {

        private lateinit var movieDetail: Model.Movie

        fun bind(movie: Model.Movie) = with(itemView) {
            movieDetail = movie
            binding.movie = movie
        }

        init {
            binding.movieItem.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            if (view != null) {
                listener.onClick(movieDetail)
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
        holder.bind(getItem(position))
    }

    fun setOnItemClickListener(aClickListener: Any) {
        listener = aClickListener as ClickListener
    }

    interface ClickListener {
        fun onClick(movie:Model.Movie)//{}
    }
}

class DiffCallback : DiffUtil.ItemCallback<Model.Movie>() {
    override fun areItemsTheSame(oldItem: Model.Movie, newItem: Model.Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Model.Movie, newItem: Model.Movie): Boolean {
        return oldItem == newItem
    }

}