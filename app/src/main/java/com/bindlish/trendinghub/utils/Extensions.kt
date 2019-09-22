package com.bindlish.trendinghub.utils

import com.bindlish.trendinghub.data.network.Resource
import retrofit2.Response

/**
 * Created by Aman Bindlish on 22,September,2019
 */
/**
 * Converts Retrofit [Response] to [Resource] which provides state
 * and data to the UI.
 */
fun <ResultType> Response<ResultType>.toResource(): Resource<ResultType> {
    val error = errorBody()?.toString() ?: message()
    return when {
        isSuccessful -> {
            val body = body()
            when {
                body != null -> Resource.success(body)
                else -> Resource.error(error)
            }
        }
        else -> Resource.error(error)
    }
}