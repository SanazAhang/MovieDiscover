package com.hamipishgaman.moviediscover.data.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") ApiKey:String,
        @Query("release_date.gte")releaseDateGTE:String,
        @Query("release_date.lte")releaseDateLTE: String): Response<Dto.Result>
}