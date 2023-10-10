package com.goodaysolutions.waltmartchallenge.home.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.goodaysolutions.waltmartchallenge.R
import com.goodaysolutions.waltmartchallenge.common.extensions.setFormattedCurrency
import com.goodaysolutions.waltmartchallenge.core.view.BaseInnerFragment
import com.goodaysolutions.waltmartchallenge.databinding.FragmentHomeDetailBinding
import com.goodaysolutions.waltmartchallenge.home.view.definition.HomeDetailView
import com.goodaysolutions.waltmartchallenge.home.view.definition.HomeHostView
import com.goodaysolutions.waltmartchallenge.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeDetailFragment : BaseInnerFragment<
        HomeViewModel,
        FragmentHomeDetailBinding,
        HomeHostView,
        HomeDetailView>(), HomeDetailView {

    override fun getHostFragmentViewClass(): Class<HomeHostView> = HomeHostView::class.java

    override fun getInnerFragmentViewClass(): Class<HomeDetailView> = HomeDetailView::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_home_detail

    override fun handleBackCallback(): Boolean = true

    override fun onBackPressed() {
        goBack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = mViewDataBinding.apply {
        Glide.with(iv)
            .load(mViewModel.item.thumbnail)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .placeholder(R.mipmap.ic_launcher)
            .into(iv)


        tvTitle.text = mViewModel.item.title
        tvDesc.text = mViewModel.item.price.setFormattedCurrency()

    }

    override fun goBack() {
        mTouchManager.blockTouchEventsTemporarily()
        findNavController().popBackStack()
    }

}