package com.goodaysolutions.waltmartchallenge.home.viewmodel

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.goodaysolutions.waltmartchallenge.BR
import com.goodaysolutions.waltmartchallenge.core.data.api.models.responses.Result
import com.goodaysolutions.waltmartchallenge.core.data.api.models.responses.SearchItemsResponse
import com.goodaysolutions.waltmartchallenge.core.data.local.Item
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
    }

    var query = ""


    var saveButtonVisible = false
    @Bindable get

    private var resultsList = listOf<Item>()
        set(value) {
            field = value
            saveButtonVisible = resultsList.isNotEmpty()
            notifyPropertyChanged(BR.saveButtonVisible)
        }

    lateinit var item: Item

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
        response.body()?.results?.let { resultList ->
            val auxList = mutableListOf<Item>()
            resultList.forEach {
                auxList.add(
                    Item(
                        id = it.id,
                        title = it.title,
                        price = it.price,
                        thumbnail = it.thumbnail
                    )
                )
            }
            resultsList = auxList

        }
        updateState(HomeViewState.OnSuccessSearchItems)
        hideLoading()
    }

    fun getItems() = resultsList

    fun getItemsFromDataBase() {
        viewModelScope.launch {
            showLoading()
            repository.getItemList().catch {
                Log.e("erro", it.message.toString())
                handleExceptionWithViewState(it) { error ->
                    HomeViewState.OnErrorSearchItems
                }
            }.collect {
                handleSearchItemsSuccess(it)
            }
        }
    }

    private fun handleSearchItemsSuccess(items: List<Item>) {
        resultsList = items
        updateState(HomeViewState.OnSuccessSearchItems)
        hideLoading()
    }

    fun insertItemsToDataBase() {
        viewModelScope.launch {
            showLoading()
            repository.insertItemList(resultsList).catch {
                Log.e("erro", it.message.toString())
                handleExceptionWithViewState(it) { error ->
                    HomeViewState.OnErrorSavedItems
                }
            }.collect {
                hideLoading()
                updateState(HomeViewState.OnSuccessSavedItems)
            }
        }
    }

}