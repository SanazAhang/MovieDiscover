package com.hamipishgaman.moviediscover.ui.movielist

import androidx.lifecycle.*
import com.hamipishgaman.moviediscover.domain.model.Model
import com.hamipishgaman.moviediscover.domain.model.ResultData
import com.hamipishgaman.moviediscover.domain.usecase.movie.DateFilter
import com.hamipishgaman.moviediscover.domain.usecase.movie.MovieGetUseCase
import com.hamipishgaman.moviediscover.domain.usecase.movie.MovieRefreshUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListMovieViewModel @Inject constructor(
    state: SavedStateHandle,
    private var movieRefreshUseCase: MovieRefreshUseCase,
    private var movieGetUseCase: MovieGetUseCase
) : ViewModel() {

    companion object {
        const val TO_DATE = "TO_DATE"
        const val FROM_DATE = "FROM_DATE"
    }

    private val vMState = state

    private var _dateTo: MutableLiveData<String> = vMState.getLiveData(TO_DATE)
    private var _dateFrom: MutableLiveData<String> = vMState.getLiveData(FROM_DATE)

    val dateTo: LiveData<String> = _dateTo
    val dateFrom: LiveData<String> = _dateFrom

    private val _movies: MutableLiveData<ConsumableValue<List<Model.Movie>>> = MutableLiveData()
    val movies: LiveData<ConsumableValue<List<Model.Movie>>> = _movies

    private val _loading: MutableLiveData<ConsumableValue<Boolean>> = MutableLiveData()
    val loading: LiveData<ConsumableValue<Boolean>> = _loading

    private val _failure: MutableLiveData<ConsumableValue<String>> = MutableLiveData()
    val failure: LiveData<ConsumableValue<String>> = _failure

    private var _error: MutableLiveData<ConsumableValue<Throwable>> = MutableLiveData()
    val error: LiveData<ConsumableValue<Throwable>> = _error


    init {
        val currentDate = null
        val startDate = null
        val dateFilter = DateFilter(startDate, currentDate)
        refresh(dateFilter)
        viewModelScope.launch {
            movieGetUseCase.execute(Unit).collect {
                _movies.value = ConsumableValue(it)
            }
        }

    }

    fun saveDate(key: String, date: String) {

        vMState.set(key, date)
    }

    fun refresh(dateFilter: DateFilter) {
        _loading.value = ConsumableValue(true)
        viewModelScope.launch {
            val resourceData = movieRefreshUseCase.execute(dateFilter)
            when (resourceData) {

                is ResultData.Success -> {
                    _loading.value = ConsumableValue(false)
                    _movies.value = ConsumableValue(resourceData.value)

                }
                is ResultData.Failure -> {
                    _loading.value = ConsumableValue(false)
                    _failure.value = ConsumableValue(resourceData.message)
                }
                is ResultData.Error -> {
                    _loading.value = ConsumableValue(false)
                    _error.value = ConsumableValue(resourceData.throwable)
                }

            }
        }
    }
}