package com.goodaysolutions.waltmartchallenge.home.view.definition

import com.goodaysolutions.waltmartchallenge.core.viewmodel.state.BaseViewState

sealed class HomeViewState : BaseViewState() {

    object OnSuccessSearchItems :
        HomeViewState()

    object OnErrorSearchItems :
        HomeViewState()
}