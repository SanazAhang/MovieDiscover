package com.hamipishgaman.moviediscover.data.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class Dto {

    @JsonClass(generateAdapter = true)
    data class Result(
        @Json(name = "results") val movies: List<Movie>
    ) : Dto()

    @JsonClass(generateAdapter = true)
    data class Movie(
        @Json(name = "id") val id: String,
        @Json(name = "original_title") val title: String,
        @Json(name = "overview") val overView: String,
        @Json(name = "poster_path") val posterURL: String,
        @Json(name = "release_date") val releaseDate: String,
        @Json(name = "vote_average") val voteAverage: String
    ) : Dto()
}