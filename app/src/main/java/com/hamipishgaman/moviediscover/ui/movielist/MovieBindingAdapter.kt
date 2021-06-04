package com.hamipishgaman.moviediscover.ui.movielist

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hamipishgaman.moviediscover.BuildConfig

@BindingAdapter("movieImage")
fun loadImage(view: ImageView, posterUrl: String) {
    val correctPosterUrl = BuildConfig.POSTER_URL + "/t/p/w600_and_h900_bestv2/" + posterUrl

    Glide.with(view.context)
        .load(correctPosterUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}