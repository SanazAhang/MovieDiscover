package com.hamipishgaman.moviediscover.domain.model


sealed class ResultData<out T> {

    data class Success<out T>(val value: T) : ResultData<T>()

    data class Failure<out T>(val message: String) : ResultData<T>()

    data class Loading<out T>(val value: T? = null) : ResultData<T>()

    data class Error(val throwable: Throwable) : ResultData<Nothing>()

    companion object {

        fun <T> loading(value: T?): ResultData<T> = Loading(value)

        fun <T> success(value: T): ResultData<T> = Success(value)

        fun <T> failure(error_msg: String): ResultData<T> = Failure(error_msg)

        fun <T> error(throwable: Throwable): ResultData<T> = Error(throwable)

    }
}

inline fun <T> ResultData<T>.onSuccess(block: (T) -> Unit): ResultData<T> {
    if (this is ResultData.Success) {
        block(this.value)
    }
    return this
}

inline fun <T, O> ResultData<T>.map(block: (T) -> O): ResultData<O> =
    when (this) {
        is ResultData.Success -> ResultData.Success(block(value))
        is ResultData.Failure -> ResultData.Failure(message)
        is ResultData.Error -> ResultData.Error(throwable)
        is ResultData.Loading -> ResultData.Loading()
    }