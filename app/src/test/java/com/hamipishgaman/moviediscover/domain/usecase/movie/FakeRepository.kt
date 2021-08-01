package com.hamipishgaman.moviediscover.domain.usecase.movie

import com.hamipishgaman.moviediscover.domain.model.Model
import com.hamipishgaman.moviediscover.domain.model.ResultData
import com.hamipishgaman.moviediscover.domain.model.onSuccess
import com.hamipishgaman.moviediscover.domain.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository(private val resultData:ResultData<List<Model.Movie>>):MovieRepository {

    override suspend fun refresh(
        releaseDateGTE: String?,
        releaseDateLTE: String?
    ): ResultData<List<Model.Movie>> {
        return resultData
    }

    override fun get(): Flow<List<Model.Movie>> {
        var movies = listOf<Model.Movie>()
     resultData.onSuccess {
            movies = it
        }
        return flow { emit(movies) }

    }
}