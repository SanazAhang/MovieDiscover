package com.hamipishgaman.moviediscover.domain.repository.movie

import com.google.common.truth.Truth.assertThat
import com.hamipishgaman.moviediscover.data.source.FakeLocalDataSource
import com.hamipishgaman.moviediscover.data.source.FakeRemoteDataSource
import com.hamipishgaman.moviediscover.domain.model.Model
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRepositoryImpTest {
    private val movieOne = Model.Movie("1","movie1","ddddddd","dd","23-3-2002","3")
    private val movieTwo = Model.Movie("2","movie2","sdfgss","dd","23-3-2002","3")
    private val movieThree = Model.Movie("3","movie3","klmlml","dd","23-3-2002","3")
    private val movieList = listOf<Model.Movie>(movieOne,movieTwo,movieThree)
    private lateinit var localLocalDataSource:FakeLocalDataSource
    private lateinit var remoteDataSource:FakeRemoteDataSource

    private lateinit var movieRepository: MovieRepositoryImp

    @Before
    fun createRepository(){
        localLocalDataSource = FakeLocalDataSource(movieList.toMutableList())
        remoteDataSource = FakeRemoteDataSource(movieList.toMutableList())

        movieRepository = MovieRepositoryImp(remoteDataSource,localLocalDataSource)
    }

    @Test
    fun getMovies_requestAllMovieFromLocalDataSource() = runBlockingTest{

        var movies = listOf<Model.Movie>()
        movieRepository.get().collect {
            movies = it
        }

        assertThat(movieList).isEqualTo(movies)
    }

}