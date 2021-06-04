package com.hamipishgaman.moviediscover.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamipishgaman.moviediscover.domain.model.Model
import com.hamipishgaman.moviediscover.domain.model.ResultData
import com.hamipishgaman.moviediscover.domain.usecase.movie.DateFilter
import com.hamipishgaman.moviediscover.domain.usecase.movie.MovieGetUseCase
import com.hamipishgaman.moviediscover.domain.usecase.movie.MovieRefreshUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMovieViewModel @Inject constructor(
    private var movieRefreshUseCase: MovieRefreshUseCase,
    private var movieGetUseCase: MovieGetUseCase
) : ViewModel() {

    private val _movies: MutableLiveData<List<Model.Movie>> = MutableLiveData()
    val movies: LiveData<List<Model.Movie>> = _movies

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _failure: MutableLiveData<String> = MutableLiveData()
    val failure: LiveData<String> = _failure

    private var _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> = _error


    init {
         val currentDate = null
         val startDate = null
         val dateFilter = DateFilter(startDate,currentDate)
        refresh(dateFilter)
        viewModelScope.launch {
            movieGetUseCase.execute(Unit).collect {
                _movies.value = it
            }

        }

    }

     fun refresh(dateFilter:DateFilter) {
        _loading.postValue(true)
        viewModelScope.launch {
            val resourceData = movieRefreshUseCase.execute(dateFilter)
            when (resourceData) {
                //TODO change to value
                is ResultData.Success -> {
                    _loading.postValue(false)
                    _movies.value = resourceData.value

                }
                is ResultData.Failure -> {
                    _loading.postValue(false)
                    _failure.postValue(resourceData.message)
                }
                is ResultData.Error -> {
                    _loading.postValue(false)
                    _error.postValue(resourceData.throwable)
                }
                else -> {
                }
            }
        }
    }
}