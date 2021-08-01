package com.hamipishgaman.moviediscover.domain.usecase.movie

import com.google.common.truth.Truth.assertThat
import com.hamipishgaman.moviediscover.domain.model.Model
import com.hamipishgaman.moviediscover.domain.model.ResultData
import junit.framework.TestCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class MovieRefreshUseCaseTest {
    private val movieOne = Model.Movie("1", "movie1", "ddddddd", "dd", "23-3-2002", "3")
    private val movieTwo = Model.Movie("2", "movie2", "sdfgss", "dd", "23-3-2002", "3")
    private val movieList = listOf(movieOne, movieTwo)
    private val movieEmptyList = listOf<Model.Movie>()
    private val resultDataEmpty = ResultData.Success(movieEmptyList)
    private val resultData = ResultData.Success(movieList)

    private lateinit var fakeRepository: FakeRepository
    private lateinit var getUseCaseTest: MovieGetUseCase

    @Test
    fun getMovie_Repository() = runBlockingTest {
        fakeRepository = FakeRepository(resultData)
        getUseCaseTest = MovieGetUseCase(fakeRepository)

        var result = listOf<Model.Movie>()
        getUseCaseTest.execute(Unit).collect {
            result = it
        }

        assertThat(movieList).isEqualTo(result)
    }

    @Test
    fun getMovie_listSize_zero() = runBlockingTest {

        fakeRepository = FakeRepository(resultDataEmpty)
        getUseCaseTest = MovieGetUseCase(fakeRepository)
        var result = listOf<Model.Movie>()
        getUseCaseTest.execute(Unit).collect {
            result = it
        }
        assertThat(result.size).isEqualTo(0)

    }
}