package com.goodaysolutions.waltmartchallenge.core.view.definition

import com.goodaysolutions.waltmartchallenge.core.viewmodel.BaseViewModel

interface BaseHostFragmentView<VM : BaseViewModel<*>> {
    fun getViewModel(): VM
}