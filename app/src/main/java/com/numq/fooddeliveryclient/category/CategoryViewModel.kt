package com.numq.fooddeliveryclient.category

import com.numq.fooddeliveryclient.product.GetProductById
import com.numq.fooddeliveryclient.product.GetProductsFromCategory
import com.numq.fooddeliveryclient.state.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getProductById: GetProductById,
    private val getProductsFromCategory: GetProductsFromCategory
) : StateViewModel<CategoryState>(CategoryState()) {
    fun getProductsFromCategory(categoryId: String, skip: Long, limit: Long) =
        getProductsFromCategory.invoke(Triple(categoryId, skip, limit), onException) { products ->
            updateState {
                it.copy(products = it.products?.plus(products) ?: products)
            }
        }
}