package com.mogo.data.remote

import android.accounts.NetworkErrorException
import com.mogo.domain.utils.Result
import com.mogo.data.Constants
import com.mogo.data.Constants.GENERIC_ERROR

suspend fun <T> handleNetworkCall(lambda: suspend () -> T): Result<T> = try {
    Result.Success(lambda())
} catch (e: NetworkErrorException) {
    Result.Error(Constants.NETWORK_ERROR)
} catch (e: Exception) {
    Result.Error(GENERIC_ERROR)
}