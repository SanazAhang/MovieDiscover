package com.hamipishgaman.moviediscover.data.mapper

import com.hamipishgaman.moviediscover.data.local.Table
import com.hamipishgaman.moviediscover.data.network.api.Dto
import com.hamipishgaman.moviediscover.domain.model.Model
import com.hamipishgaman.moviediscover.domain.model.ResultData
import retrofit2.Response

inline fun <T> execute(request: () -> Response<T>): ResultData<T> =
    try {
        val response = request()
        if (response.isSuccessful) {
            ResultData.Success(response.body()!!)
        } else {
            ResultData.Failure(response.message())
        }
    } catch (ex: Exception) {
        ResultData.Error(ex)
    }


fun Dto.Movie.mapToModel() = Model.Movie(
    id = id,
    title = title,
    voteAverage = voteAverage,
    overView = overView,
    posterURL = posterURL,
    releaseDate = releaseDate
)

fun Model.Movie.mapToEntity() = Table.MovieEntity(
    id = id,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    overView = overView,
    title = title,
    posterURL = posterURL
)


fun Table.MovieEntity.mapToModel() = Model.Movie(
    id = id,
    title = title,
    overView = overView,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    posterURL = posterURL
)