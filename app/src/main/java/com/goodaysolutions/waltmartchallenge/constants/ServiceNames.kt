package com.goodaysolutions.waltmartchallenge.constants

import com.goodaysolutions.waltmartchallenge.BuildConfig


object ServiceNames {

    //Header Accept-Language
    const val ACCEPT_LANGUAGE_HEADER = "Accept-Language"
    const val ACCEPT_LANGUAGE_HEADER_VALUE = "SPA"

    //Content-Type

    //AUX
    private const val CONTENT_TYPE = "Content-Type"
    const val AUTHORIZATION = "Authorization"
    const val CONTENT_TYPE_APPLICATION_JSON = "$CONTENT_TYPE: application/json"


    //Registry refactorized
    const val SEARCH_ITEMS = "${BuildConfig.SERVER_URL}search"

}
