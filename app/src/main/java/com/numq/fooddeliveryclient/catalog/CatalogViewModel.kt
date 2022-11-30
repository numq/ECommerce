package com.numq.fooddeliveryclient.catalog

import com.numq.fooddeliveryclient.category.GetCategories
import com.numq.fooddeliveryclient.state.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getCategories: GetCategories
) : StateViewModel<CatalogState>(CatalogState()) {
    fun getCategories() = getCategories.invoke(Unit, onException) { categories ->
        updateState {
            it.copy(categories = categories)
        }
    }

    init {
        getCategories()
    }
}