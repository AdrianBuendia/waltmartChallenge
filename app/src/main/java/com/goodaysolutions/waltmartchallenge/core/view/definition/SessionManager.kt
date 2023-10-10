package com.goodaysolutions.waltmartchallenge.core.view.definition

interface SessionManager {
    fun registerCallback(
        performSignOutCallback: () -> Unit
    )
}