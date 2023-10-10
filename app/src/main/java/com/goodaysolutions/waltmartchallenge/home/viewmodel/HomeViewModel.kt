package com.goodaysolutions.waltmartchallenge.home.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.goodaysolutions.waltmartchallenge.core.data.api.models.responses.Result
import com.goodaysolutions.waltmartchallenge.core.data.api.models.responses.SearchItemsResponse
import com.goodaysolutions.waltmartchallenge.core.data.repository.WaltmartChallengeRepo
import com.goodaysolutions.waltmartchallenge.core.viewmodel.BaseViewModel
import com.goodaysolutions.waltmartchallenge.home.view.definition.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: WaltmartChallengeRepo
) : BaseViewModel<WaltmartChallengeRepo>(repository) {

    companion object {
        const val EMPTY = ""
        const val DEAFULT_SEARCH = "Motorola G6"
    }

    var query = ""

    private var resultsList = listOf<Result>()

    lateinit var item: Result

    fun searchItems(query: String = EMPTY) {
        viewModelScope.launch {
            showLoading()
            repository.searchItems(query).catch {
                Log.e("erro", it.message.toString())
                handleExceptionWithViewState(it) { error ->
                    HomeViewState.OnErrorSearchItems
                }
            }.collect {
                handleSearchItemsSuccess(it)
            }
        }
    }

    private fun handleSearchItemsSuccess(response: Response<SearchItemsResponse>) {
        response.body()?.results?.let { resultsList = it }
        updateState(HomeViewState.OnSuccessSearchItems)
        hideLoading()
    }

    fun getItems() = resultsList

}