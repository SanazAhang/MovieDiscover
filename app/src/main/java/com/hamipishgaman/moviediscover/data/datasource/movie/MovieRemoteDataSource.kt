package com.hamipishgaman.moviediscover.data.datasource.movie

import com.hamipishgaman.moviediscover.data.datasource.RemoteDataSource
import com.hamipishgaman.moviediscover.domain.model.Model
import com.hamipishgaman.moviediscover.domain.model.ResultData

interface MovieRemoteDataSource: RemoteDataSource<Model.Movie> {

    suspend fun getData(releaseDateGTE:String?,releaseDateLTE: String?):
            ResultData<List<Model.Movie>>
}