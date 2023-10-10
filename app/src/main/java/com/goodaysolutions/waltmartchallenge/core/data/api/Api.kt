package com.goodaysolutions.waltmartchallenge.core.data.api

import com.goodaysolutions.waltmartchallenge.constants.ServiceNames
import com.goodaysolutions.waltmartchallenge.core.data.api.models.responses.SearchItemsResponse
import retrofit2.Response
import retrofit2.http.*


interface Api {

    @GET(ServiceNames.SEARCH_ITEMS)
    suspend fun searchItems(
        @Query("q") q: String,
    ): Response<SearchItemsResponse>

}
