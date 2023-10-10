package com.goodaysolutions.waltmartchallenge.core.view.definition

import com.goodaysolutions.waltmartchallenge.core.viewmodel.state.BaseViewState

sealed class CommonViewState : BaseViewState() {
    object OnSuccessSignOutRequest : BaseViewState()
}