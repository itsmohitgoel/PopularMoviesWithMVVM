package com.mogo.domain.utils

sealed interface Result<T>{
    class Success<T>(val data : T) : Result<T>
    class Error<T>(val message : String) : Result<T>
    class Loading<T> : Result<T>
}