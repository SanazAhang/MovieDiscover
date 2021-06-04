package com.hamipishgaman.moviediscover.data.datasource.movie

import com.hamipishgaman.moviediscover.domain.model.Model
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {

    fun fetch(): Flow<List<Model.Movie>>

    suspend fun persist(movies: List<Model.Movie>)
}