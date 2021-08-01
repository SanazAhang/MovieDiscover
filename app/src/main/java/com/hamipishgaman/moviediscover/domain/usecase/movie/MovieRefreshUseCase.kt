package com.hamipishgaman.moviediscover.domain.usecase.movie

import com.hamipishgaman.moviediscover.domain.model.Model
import com.hamipishgaman.moviediscover.domain.model.ResultData
import com.hamipishgaman.moviediscover.domain.repository.movie.MovieRepository
import com.hamipishgaman.moviediscover.domain.usecase.BaseUseCase

class MovieRefreshUseCase  constructor(private val movieRepository: MovieRepository) :
    BaseUseCase<DateFilter, ResultData<List<Model.Movie>>> {

    override suspend fun execute(input: DateFilter): ResultData<List<Model.Movie>> {
        return movieRepository.refresh(input.releaseDateGTE,input.releaseDateLTE)
    }
}

