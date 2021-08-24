package com.hamipishgaman.moviediscover.data.datasource.movie

import com.hamipishgaman.moviediscover.data.mapper.execute
import com.hamipishgaman.moviediscover.data.mapper.mapToModel
import com.hamipishgaman.moviediscover.data.network.api.MovieApi
import com.hamipishgaman.moviediscover.domain.model.Model
import com.hamipishgaman.moviediscover.domain.model.ResultData
import com.hamipishgaman.moviediscover.domain.model.map
import javax.inject.Inject

class MovieRemoteDataSourceImp @Inject constructor(private val movieAPI:MovieApi):
    MovieRemoteDataSource {
    private val apiKey = "c988e17128ee22ab8bda0b967e5653d2"

    override suspend fun getData(
        releaseDateGTE: String?,
        releaseDateLTE: String?
    ): ResultData<List<Model.Movie>> = execute{
        movieAPI.getMovies(apiKey,releaseDateGTE,releaseDateLTE)
    }.map { result ->
        result.movies.map { dtoMovie ->
            dtoMovie.mapToModel()
        }

    }
}