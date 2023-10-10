package com.goodaysolutions.waltmartchallenge.home.view

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.goodaysolutions.waltmartchallenge.R
import com.goodaysolutions.waltmartchallenge.common.extensions.hideKeyboard
import com.goodaysolutions.waltmartchallenge.common.views.GenericBottomSheet
import com.goodaysolutions.waltmartchallenge.common.views.adapters.HomeItemListAdapter
import com.goodaysolutions.waltmartchallenge.core.view.BaseInnerFragment
import com.goodaysolutions.waltmartchallenge.core.viewmodel.state.BaseViewState
import com.goodaysolutions.waltmartchallenge.databinding.FragmentHomeBinding
import com.goodaysolutions.waltmartchallenge.home.view.definition.HomeHostView
import com.goodaysolutions.waltmartchallenge.home.view.definition.HomeView
import com.goodaysolutions.waltmartchallenge.home.view.definition.HomeViewState
import com.goodaysolutions.waltmartchallenge.home.viewmodel.HomeViewModel
import com.goodaysolutions.waltmartchallenge.home.viewmodel.HomeViewModel.Companion.DEAFULT_SEARCH
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : BaseInnerFragment<
        HomeViewModel,
        FragmentHomeBinding,
        HomeHostView,
        HomeView
        >(), HomeView {

    @Inject
    lateinit var itemListAdapter: HomeItemListAdapter

    override fun getHostFragmentViewClass(): Class<HomeHostView> = HomeHostView::class.java

    override fun getInnerFragmentViewClass(): Class<HomeView> = HomeView::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchInitialData()
        initEdiTextAction()
        initListAdapter()
        fillAdapter()
    }

    private fun initEdiTextAction() = with(mViewDataBinding) {
        et.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchItem()
            }
            true
        }
    }

    private fun fetchInitialData() {
        if (mViewModel.getItems().isEmpty()) mViewModel.searchItems(DEAFULT_SEARCH)
    }

    override fun renderViewState(state: BaseViewState) {
        when (state) {
            is HomeViewState.OnSuccessSearchItems -> fillAdapter()
            is HomeViewState.OnErrorSearchItems -> showError()
        }
    }

    override fun searchItem() = with(mViewModel) {
        hideKeyboard()
        searchItems(query)
    }

    private fun initListAdapter() {
        mViewDataBinding.rv.adapter = itemListAdapter
        itemListAdapter.onItemClickListener = { result ->
            mViewModel.item = result
            goTo(
                HomeFragmentDirections.actionHomeFragmentToHomeDetailFragment()
            )
        }
    }

    private fun fillAdapter() {
        if (mViewModel.getItems().isNotEmpty())
            itemListAdapter.submitList(mViewModel.getItems())
    }

    private fun showError() {
        val drop = GenericBottomSheet()
        drop.onClickListener = {
            if (mViewModel.query.isNotEmpty()) searchItem()
            else fetchInitialData()
        }
        hideKeyboard()
        drop.showNow(parentFragmentManager, GenericBottomSheet.TAG)
    }

}