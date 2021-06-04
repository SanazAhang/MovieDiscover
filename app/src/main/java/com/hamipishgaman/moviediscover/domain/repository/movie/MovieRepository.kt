package com.hamipishgaman.moviediscover.domain.repository.movie

import com.hamipishgaman.moviediscover.domain.model.Model
import com.hamipishgaman.moviediscover.domain.model.ResultData
import com.hamipishgaman.moviediscover.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

interface MovieRepository :
    Repository<Model.Movie> {

    suspend fun refresh(releaseDateGTE:String?,releaseDateLTE: String?): ResultData<List<Model.Movie>>

    fun get(): Flow<List<Model.Movie>>
}