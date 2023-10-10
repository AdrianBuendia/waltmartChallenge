package com.goodaysolutions.waltmartchallenge.core.viewmodel

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodaysolutions.waltmartchallenge.constants.CommonErrors
import com.goodaysolutions.waltmartchallenge.core.data.repository.WaltmartChallengeRepo
import com.goodaysolutions.waltmartchallenge.core.data.repository.SessionExpiredException
import com.goodaysolutions.waltmartchallenge.core.util.requestHandler.HandlerViewModelStateListener
import com.goodaysolutions.waltmartchallenge.core.viewmodel.state.BaseViewState
import com.goodaysolutions.waltmartchallenge.core.view.definition.CommonViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

@ExperimentalCoroutinesApi
open class BaseViewModel<REPOSITORY : WaltmartChallengeRepo> @Inject constructor(protected val repository: REPOSITORY) :
    ViewModel(), Observable, HandlerViewModelStateListener {

    val dataLoading = MutableLiveData(false)

    val viewState: LiveData<BaseViewState>
        get() = innerViewState

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }
    private val innerViewState: MutableLiveData<BaseViewState> = MutableLiveData()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.remove(callback)
    }

    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }

    fun clearState() {
        updateState(BaseViewState.ClearState)
    }

    override fun showLoading() {
        dataLoading.value = true
    }

    override fun hideLoading() {
        dataLoading.value = false
    }

    override fun updateState(state: BaseViewState) {
        innerViewState.value = state
    }

    override fun handleExceptionWithViewState(t: Throwable?, state: (Int) -> BaseViewState) {
        hideLoading()
        val error = handleRequestException(t)
        val viewState = if (t is SessionExpiredException) {
            BaseViewState.SessionExpiredState
        } else {
            state(error)
        }
        updateState(viewState)
    }

    private fun handleRequestException(t: Throwable?): Int = when (t) {
        is ConnectException, is UnknownHostException -> CommonErrors.CONNECTION_ERROR
        is SessionExpiredException -> CommonErrors.SESSION_EXPIRED_ERROR
        else -> {
            CommonErrors.DEFAULT_ERROR
        }
    }

    fun signOut() {
        viewModelScope.launch {
            showLoading()
            repository.signOut()
                .catch { handleSignOutResponse() }
                .collect {
                    //clearDataSession()
                    handleSignOutResponse()
                }
        }
    }

    private fun handleSignOutResponse() {
        hideLoading()
        //clearParams()
        updateState(CommonViewState.OnSuccessSignOutRequest)
    }

}