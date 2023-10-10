package com.goodaysolutions.waltmartchallenge.core.data.repository

import com.goodaysolutions.waltmartchallenge.common.exceptions.ApiException
import com.goodaysolutions.waltmartchallenge.constants.ApiCodes
import com.goodaysolutions.waltmartchallenge.core.data.api.Api
import com.goodaysolutions.waltmartchallenge.core.data.api.models.responses.SearchItemsResponse
import com.goodaysolutions.waltmartchallenge.core.data.local.ItemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

typealias MeliResponse<T> = Flow<Response<T>>

@ExperimentalCoroutinesApi
open class MeliChallengeRepo @Inject constructor(
    protected val api: Api,
    protected val itemDao: ItemDao
) {

    protected suspend fun <R> makeRequest(
        vararg successfulCodes: Int = intArrayOf(ApiCodes.SUCCESS),
        request: suspend () -> Response<R>,
    ): Response<R> {
        val requestResponse = request.invoke()
        return when (requestResponse.code()) {
            in successfulCodes -> requestResponse
            ApiCodes.SESSION_EXPIRED -> throw SessionExpiredException()
            else -> throw ApiException()
        }
    }

    fun searchItems(q: String): MeliResponse<SearchItemsResponse> = flow {
        val response = makeRequest { api.searchItems(q = q) }
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun signOut(token: String? = null): Flow<Boolean> = flow {
        emit(true)
    }.flowOn(Dispatchers.IO)


}

class SessionExpiredException : ApiException()