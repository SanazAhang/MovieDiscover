package com.hamipishgaman.moviediscover.domain.usecase.movie


import com.hamipishgaman.moviediscover.domain.model.Model
import com.hamipishgaman.moviediscover.domain.repository.movie.MovieRepository
import com.hamipishgaman.moviediscover.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow

class MovieGetUseCase(private val movieRepository: MovieRepository) :
    BaseUseCase<Unit, Flow<List<Model.Movie>>> {
    override suspend fun execute(input: Unit): Flow<List<Model.Movie>> =
        movieRepository.get()
}

