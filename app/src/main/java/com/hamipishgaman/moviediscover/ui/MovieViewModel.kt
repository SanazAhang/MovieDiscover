package com.hamipishgaman.moviediscover.ui

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
import java.text.SimpleDateFormat
import java.util.*
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
         val sdf = SimpleDateFormat.getDateInstance()
         val currentDate: String = sdf.format(Date())
         val startDate = "1990.1.1"
         val dateFilter = DateFilter(startDate,currentDate)
        refresh(dateFilter)
        viewModelScope.launch {
            movieGetUseCase.execute(Unit).collect {
                _movies.value = it
            }

        }

    }

    private fun refresh(dateFilter:DateFilter) {
        _loading.postValue(false)
        viewModelScope.launch {
            val resourceData = movieRefreshUseCase.execute(dateFilter)
            when (resourceData) {
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