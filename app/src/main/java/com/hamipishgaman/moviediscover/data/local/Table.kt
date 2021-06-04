package com.hamipishgaman.moviediscover.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class Table {

    @Entity
    data class MovieEntity(
        @ColumnInfo(name = "id") @PrimaryKey val id: String,
        @ColumnInfo(name = "original_title") val title: String,
        @ColumnInfo(name = "over_view") val overView: String,
        @ColumnInfo(name = "release_date") val releaseDate: String,
        @ColumnInfo(name = "vote_average") val voteAverage: String,
        @ColumnInfo(name = "poster_url") val posterURL: String
    ) : Table()

}