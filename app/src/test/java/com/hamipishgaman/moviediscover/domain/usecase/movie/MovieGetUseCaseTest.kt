package com.hamipishgaman.moviediscover.domain.usecase.movie

import com.google.common.truth.Truth.assertThat
import com.hamipishgaman.moviediscover.domain.model.Model
import com.hamipishgaman.moviediscover.domain.model.ResultData
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class MovieGetUseCaseTest{
    private val movieOne = Model.Movie("1","movie1","ddddddd","dd","23-3-2002","3")
    private val movieTwo = Model.Movie("2","movie2","sdfgss","dd","23-3-2002","3")
    private val movieList = listOf(movieOne,movieTwo)

    private val resultData = ResultData.Success(movieList)

    private val dateFilter = DateFilter(null,null)
    private lateinit var fakeRepository:FakeRepository
    private lateinit var refreshUseCaseTest: MovieRefreshUseCase

    @Before
    fun createUseCase(){
        fakeRepository = FakeRepository(resultData)
        refreshUseCaseTest = MovieRefreshUseCase(fakeRepository)
    }

    @Test
    fun getMovies_Success() = runBlockingTest{
        val result = refreshUseCaseTest.execute(dateFilter)
        assertThat(resultData).isEqualTo(result)
    }

}