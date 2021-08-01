package com.hamipishgaman.moviediscover.data.source

import com.hamipishgaman.moviediscover.data.datasource.movie.MovieRemoteDataSource
import com.hamipishgaman.moviediscover.domain.model.Model
import com.hamipishgaman.moviediscover.domain.model.ResultData

class FakeRemoteDataSource(var movie :List<Model.Movie>):MovieRemoteDataSource {
    override suspend fun getData(
        releaseDateGTE: String?,
        releaseDateLTE: String?
    ): ResultData<List<Model.Movie>> {
        TODO("Not yet implemented")
    }
}