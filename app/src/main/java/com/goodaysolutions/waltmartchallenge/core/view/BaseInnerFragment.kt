package com.goodaysolutions.waltmartchallenge.core.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.goodaysolutions.waltmartchallenge.BR
import com.goodaysolutions.waltmartchallenge.core.view.definition.BaseHostFragmentView
import com.goodaysolutions.waltmartchallenge.core.view.definition.BaseInnerFragmentView
import com.goodaysolutions.waltmartchallenge.core.view.definition.TouchManager
import com.goodaysolutions.waltmartchallenge.core.viewmodel.BaseViewModel
import com.goodaysolutions.waltmartchallenge.core.viewmodel.state.BaseViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 *
 * All inner fragments (contained by other fragments) should ideally extend from
 * this base class to keep everything well defined.
 *
 * @param VIEW_MODEL refers to the ViewModel class of the feature.
 *
 * @param DATA_BINDING refers to the auto-generated DataBinding class for your fragment.
 *
 * @param HOST_VIEW refers to the interface implemented by your
 * host fragment. (It extends from [BaseHostFragmentView])
 *
 * @param INNER_VIEW refers to the interface implemented by your inner fragment,
 * it must be implemented with data-binding purposes. (It extends from [BaseInnerFragmentView])
 *
 * There's no need to be careful about the objects defined on this class,
 * all of them are already initialized and well controlled, you only need to
 * override certain methods to make it work according to your requirements.
 *
 */
@ExperimentalCoroutinesApi
abstract class BaseInnerFragment<
        VIEW_MODEL : BaseViewModel<*>,
        DATA_BINDING : ViewDataBinding,
        HOST_VIEW : BaseHostFragmentView<VIEW_MODEL>,
        INNER_VIEW : BaseInnerFragmentView,
        > :
    Fragment() {

    protected lateinit var mViewModel: VIEW_MODEL
    protected lateinit var mHostFragmentView: HOST_VIEW
    protected lateinit var mTouchManager: TouchManager
    protected var mIsRestoredFromBackStack = false
    lateinit var mViewDataBinding: DATA_BINDING
    private lateinit var mBackCallback: OnBackPressedCallback

    /**
     * @return the interface implemented by your host fragment
     * to provide proper communication.
     */
    abstract fun getHostFragmentViewClass(): Class<HOST_VIEW>

    /**
     * @return the interface implemented by this fragment with
     * data-binding purposes.
     */
    abstract fun getInnerFragmentViewClass(): Class<INNER_VIEW>

    /**
     * @return the layout resource id of your fragment's view.
     */
    @LayoutRes
    abstract fun getLayoutRes(): Int

    /**
     * Override this method to implement the handling for ViewStates
     */
    protected open fun renderViewState(state: BaseViewState) {
        // Override this method if is required
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TouchManager)
            mTouchManager = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHostFragmentView = getHostFragmentView(getHostFragmentViewClass())
        mViewModel = mHostFragmentView.getViewModel()
        initializeBackPressedCallback()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        if (this::mViewDataBinding.isInitialized) return mViewDataBinding.root
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        mViewDataBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, mViewModel)
            setVariable(BR.viewInteraction, getInnerFragmentView(getInnerFragmentViewClass()))
            this@BaseInnerFragment.setupBeforeBind(this)
            executePendingBindings()
        }
        return mViewDataBinding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpObservers()
    }

    override fun onResume() {
        super.onResume()
        mIsRestoredFromBackStack = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mViewModel.clearState()
        mIsRestoredFromBackStack = true
        mViewDataBinding.unbind()
    }

    /**
     * Override this method to perform operations before executing all
     * pending bindings.
     */
    protected open fun setupBeforeBind(mViewDataBinding: DATA_BINDING) {
        // Override to do whatever you need here
    }

    /**
     * Override this method to allow your fragment to handle back
     * event at [onBackPressed].
     */
    protected open fun handleBackCallback(): Boolean = false

    /**
     * Override this method to handle back event only if you
     * got true at [handleBackCallback].
     */
    protected open fun onBackPressed() {
        // Override to handle back event
    }

    private fun setUpObservers() {
        mViewModel.dataLoading.observe(viewLifecycleOwner) { value ->
            mBackCallback.isEnabled = value || handleBackCallback()
        }
        mViewModel.viewState.observe(viewLifecycleOwner) { renderViewState(it) }
    }

    private fun getInnerFragmentView(viewClass: Class<INNER_VIEW>): INNER_VIEW {
        return viewClass.cast(this)!!
    }

    private fun getHostFragmentView(viewClass: Class<HOST_VIEW>): HOST_VIEW {
        return viewClass.cast(parentFragment?.parentFragment)!!
    }

    private fun initializeBackPressedCallback() {
        mBackCallback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (!mViewModel.dataLoading.value!!) {
                onBackPressed()
            }
        }
    }

    fun goTo(navDir: NavDirections) {
        mTouchManager.blockTouchEventsTemporarily()
        findNavController().navigate(navDir)
    }

}
