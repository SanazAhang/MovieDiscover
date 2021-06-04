package com.hamipishgaman.moviediscover.domain.model

sealed class Model {

    data class Movie(
        val id: String,
        val title: String,
        val overView: String,
        val posterURL: String,
        val releaseDate: String,
        val voteAverage: String
    ):Model()
}