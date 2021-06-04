package com.hamipishgaman.moviediscover.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Model  {

    @Parcelize
    data class Movie(
        val id: String,
        val title: String,
        val overView: String,
        val posterURL: String,
        val releaseDate: String,
        val voteAverage: String
    ) : Model(),Parcelable
}