package com.hamipishgaman.moviediscover.ui.movielist

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("movieImage")
fun loadImage(view: ImageView, profileImage: String) {
    Glide.with(view.context)
        .load(profileImage)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}