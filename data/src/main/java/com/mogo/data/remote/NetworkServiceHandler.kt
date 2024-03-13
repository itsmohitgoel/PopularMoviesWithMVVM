package com.mogo.data.remote

import com.mogo.common.utils.Resource

suspend fun <T> handleNetworkCall(lambda: suspend () -> T): Resource<T> = try {
    lambda()?.let {
        Resource.Success(it)
    } ?: Resource.Error("Constants.ERROR_FETCHING_RECORDS")
} catch (e: Exception) {
    Resource.Error("")
}