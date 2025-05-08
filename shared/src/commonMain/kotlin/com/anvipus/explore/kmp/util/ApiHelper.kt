package com.anvipus.explore.kmp.util


import com.anvipus.explore.kmp.data.model.Resource
import kotlinx.coroutines.flow.Flow

object ApiHelper {
    fun <RequestType, ResultType> call(
        apiCall: suspend () -> RequestType
    ): Flow<Resource<ResultType>> {
        val baseApiCall = object : BaseApiCall<RequestType, ResultType>() {}
        return baseApiCall.executeApiCall(apiCall)
    }
}