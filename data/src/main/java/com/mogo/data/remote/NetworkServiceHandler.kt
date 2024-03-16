package com.mogo.data.remote

import android.accounts.NetworkErrorException
import com.mogo.domain.utils.Result
import com.mogo.data.Constants
import com.mogo.data.Constants.GENERIC_ERROR

suspend fun <T> handleNetworkCall(doApiRequestAndMap: suspend () -> T): Result<T> = try {
    Result.Success(doApiRequestAndMap())
} catch (e: NetworkErrorException) {
    e.printStackTrace()
    Result.Error(Constants.NETWORK_ERROR)
} catch (e: Exception) {
    e.printStackTrace()
    Result.Error(GENERIC_ERROR)
}