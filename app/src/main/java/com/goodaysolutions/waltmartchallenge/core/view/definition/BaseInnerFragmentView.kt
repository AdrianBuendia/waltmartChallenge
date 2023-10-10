package com.goodaysolutions.waltmartchallenge.core.view.definition

import com.goodaysolutions.waltmartchallenge.core.viewmodel.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

interface BaseInnerFragmentView

@ExperimentalCoroutinesApi
interface BaseInnerFragmentOutView<VM : BaseViewModel<*>> {
    fun getViewModel(): VM
}