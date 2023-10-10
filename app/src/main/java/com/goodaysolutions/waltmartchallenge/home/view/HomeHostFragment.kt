package com.goodaysolutions.waltmartchallenge.home.view

import com.goodaysolutions.waltmartchallenge.R
import com.goodaysolutions.waltmartchallenge.core.view.BaseHostFragment
import com.goodaysolutions.waltmartchallenge.databinding.FragmentHomeHostBinding
import com.goodaysolutions.waltmartchallenge.home.view.definition.HomeHostView
import com.goodaysolutions.waltmartchallenge.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeHostFragment : BaseHostFragment<
        HomeViewModel,
        FragmentHomeHostBinding
        >(), HomeHostView {
    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun getLayoutRes(): Int = R.layout.fragment_home_host

}