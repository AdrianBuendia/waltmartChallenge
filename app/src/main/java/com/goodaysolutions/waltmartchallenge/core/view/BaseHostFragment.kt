package com.goodaysolutions.waltmartchallenge.core.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.core.view.contains
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.goodaysolutions.waltmartchallenge.BR
import com.goodaysolutions.waltmartchallenge.core.view.definition.BaseHostFragmentView
import com.goodaysolutions.waltmartchallenge.core.view.definition.CommonViewState
import com.goodaysolutions.waltmartchallenge.core.view.definition.SessionManager
import com.goodaysolutions.waltmartchallenge.core.view.definition.TouchManager
import com.goodaysolutions.waltmartchallenge.R
import com.goodaysolutions.waltmartchallenge.core.viewmodel.BaseViewModel
import com.goodaysolutions.waltmartchallenge.core.viewmodel.state.BaseViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Jolu Rivera on 2/27/20.
 */
@ExperimentalCoroutinesApi
abstract class BaseHostFragment<VIEW_MODEL : BaseViewModel<*>, DATA_BINDING : ViewDataBinding> :
    Fragment(), BaseHostFragmentView<VIEW_MODEL> {

    protected lateinit var mViewModel: VIEW_MODEL
    protected lateinit var mTouchManager: TouchManager
    private lateinit var mSessionManager: SessionManager
    lateinit var mViewDataBinding: DATA_BINDING
    private val mLoaderViewDelegate = lazy {
        View.inflate(context, R.layout.loader_view, null)
    }
    private val mLoaderView by mLoaderViewDelegate

    abstract fun getViewModelClass(): Class<VIEW_MODEL>

    @LayoutRes
    abstract fun getLayoutRes(): Int

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.viewState.observe(viewLifecycleOwner) { renderViewState(it) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TouchManager)
            mTouchManager = context
        if (context is SessionManager)
            mSessionManager = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mViewModel = ViewModelProvider(this)[getViewModelClass()]
        super.onCreate(savedInstanceState)
        mSessionManager.registerCallback(
            performSignOutCallback = { performSignOut() })
    }

    override fun onResume() {
        super.onResume()
        disableLoaderIfRequired()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        mViewDataBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, mViewModel)
            this@BaseHostFragment.setupBeforeBind(this)
            executePendingBindings()
        }

        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoaderView(view)
    }

    override fun getViewModel(): VIEW_MODEL = mViewModel


    protected open fun setupBeforeBind(mViewDataBinding: DATA_BINDING) {
        // Override to do whatever you need here
    }

    /**
     * This method must be kept private to avoid issues at inner fragments.
     * Only render common states through all the application.
     * @param baseViewState refers to current view state
     */
    private fun renderViewState(baseViewState: BaseViewState?) {
        when (baseViewState) {
            is CommonViewState.OnSuccessSignOutRequest -> {
//                findRootNavController()?.navigate(R.id.action_global_login)
            }
        }
    }

    private fun setupLoaderView(view: View) {
        if (view !is FrameLayout) {
            throw IllegalStateException("Required FrameLayout as root view")
        }

        mViewModel.dataLoading.observe(viewLifecycleOwner) { dataLoading ->
            if (dataLoading) {
                if (view.contains(mLoaderView)) view.removeView(mLoaderView)
                mTouchManager.blockTouchEvents()

                if (mLoaderView.parent != null) {
                    (mLoaderView.parent as ViewGroup).removeView(mLoaderView) // <- fix
                }
                view.addView(mLoaderView)
            } else if (mLoaderViewDelegate.isInitialized()) {
                mTouchManager.unlockTouchEvents()
                view.removeView(mLoaderView)
            }
        }
    }

    private fun disableLoaderIfRequired() {
        val dataLoading = mViewModel.dataLoading.value!!
        if (mLoaderViewDelegate.isInitialized() && !dataLoading) {
            (view as FrameLayout).removeView(mLoaderView)
            mTouchManager.unlockTouchEvents()
        }
    }

    private fun performSignOut() {
        mViewModel.signOut()
    }
}

