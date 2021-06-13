package com.hamipishgaman.moviediscover.data.datasource.movie

import com.hamipishgaman.moviediscover.data.datasource.LocalDataSource
import com.hamipishgaman.moviediscover.data.datasource.RemoteDataSource
import com.hamipishgaman.moviediscover.domain.model.Model
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource:LocalDataSource<Model.Movie> {

    fun fetch(): Flow<List<Model.Movie>>

    suspend fun persist(movies: List<Model.Movie>)
}