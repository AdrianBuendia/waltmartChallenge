package com.goodaysolutions.waltmartchallenge.core.util.requestHandler

import com.goodaysolutions.waltmartchallenge.core.viewmodel.state.BaseViewState

interface HandlerViewModelStateListener {
    fun showLoading()
    fun hideLoading()
    fun updateState(state: BaseViewState)
    fun handleExceptionWithViewState(t: Throwable?, state: (Int) -> BaseViewState)
}