package com.numq.ecommerce.category

import androidx.lifecycle.viewModelScope
import com.numq.ecommerce.viewmodel.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategories: GetCategories
) : StateViewModel<CategoryState>(CategoryState()) {
    fun getCategories(skip: Long, limit: Long) =
        getCategories.invoke(viewModelScope, Pair(skip, limit), onException) { flow ->
            viewModelScope.launch {
                val categories = flow.toList()
                updateState { it.copy(categories = it.categories.plus(categories).distinct()) }
            }
        }
}