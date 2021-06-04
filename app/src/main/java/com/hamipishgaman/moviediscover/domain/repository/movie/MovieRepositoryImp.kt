package com.hamipishgaman.moviediscover.domain.repository.movie


import com.hamipishgaman.moviediscover.data.datasource.movie.MovieLocalDataSource
import com.hamipishgaman.moviediscover.data.datasource.movie.MovieRemoteDataSource
import com.hamipishgaman.moviediscover.domain.model.Model
import com.hamipishgaman.moviediscover.domain.model.ResultData
import com.hamipishgaman.moviediscover.domain.model.onSuccess
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieRemoteSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) : MovieRepository {

    override suspend fun refresh(
        releaseDateGTE: String?,
        releaseDateLTE: String?
    ): ResultData<List<Model.Movie>> {
        return movieRemoteSource.getData(releaseDateGTE, releaseDateLTE).onSuccess {
            movieLocalDataSource.persist(it)
        }
    }

    override fun get(): Flow<List<Model.Movie>> =
        movieLocalDataSource.fetch()
}