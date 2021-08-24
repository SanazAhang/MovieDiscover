package com.hamipishgaman.moviediscover.data.datasource.movie

import com.hamipishgaman.moviediscover.data.local.MovieDao
import com.hamipishgaman.moviediscover.data.local.Table
import com.hamipishgaman.moviediscover.data.mapper.mapToEntity
import com.hamipishgaman.moviediscover.data.mapper.mapToModel
import com.hamipishgaman.moviediscover.domain.model.Model
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieLocalDataSourceImp @Inject constructor(private val movieDao: MovieDao):
    MovieLocalDataSource {

    override fun fetch(): Flow<List<Model.Movie>> {
        return movieDao.select().map {
            it.map { movies ->
                movies.mapToModel()
            }
        }
    }

    override suspend fun persist(movies: List<Model.Movie>) {
        val mutableMovies = mutableListOf<Table.MovieEntity>()
        movies.forEach { movie ->
            mutableMovies.add(movie.mapToEntity())
        }

        movieDao.insertMovies(mutableMovies)
    }
}