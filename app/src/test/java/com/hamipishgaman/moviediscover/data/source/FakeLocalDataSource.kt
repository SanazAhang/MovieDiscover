package com.hamipishgaman.moviediscover.data.source

import com.hamipishgaman.moviediscover.data.datasource.movie.MovieLocalDataSource
import com.hamipishgaman.moviediscover.domain.model.Model
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocalDataSource(var movies:List<Model.Movie> = listOf<Model.Movie>()):MovieLocalDataSource {
    override fun fetch(): Flow<List<Model.Movie>> {
        
        return flow {
            emit(movies)
        }
    }

    override suspend fun persist(movies: List<Model.Movie>) {
        TODO("Not yet implemented")
    }
}