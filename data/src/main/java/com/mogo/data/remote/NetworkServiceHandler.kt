package com.mogo.data.remote

import com.mogo.common.utils.Resource

suspend fun <T> handleNetworkCall(lambda: suspend () -> T): Resource<T> = try {
    Resource.Success(lambda())
} catch (e: Exception) {
    Resource.Error("")
}