package com.numq.ecommerce.product

import androidx.lifecycle.viewModelScope
import com.numq.ecommerce.cart.DecreaseQuantity
import com.numq.ecommerce.cart.IncreaseQuantity
import com.numq.ecommerce.viewmodel.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProducts: GetProducts,
    private val increaseQuantity: IncreaseQuantity,
    private val decreaseQuantity: DecreaseQuantity
) : StateViewModel<ProductState>(ProductState()) {
    fun getProducts(tags: Array<String>, skip: Long, limit: Long) =
        getProducts.invoke(viewModelScope, Triple(tags, skip, limit), onException) { flow ->
            viewModelScope.launch {
                val products = flow.toList()
                updateState { it.copy(products = it.products.plus(products).distinct()) }
            }
        }

    fun add(profileId: String, productId: String) =
        increaseQuantity.invoke(
            viewModelScope,
            Pair(profileId, productId),
            onException
        ) { product ->
            updateState { it.copy(products = it.products.map { item -> if (item.id == product.id) product else item }) }
        }

    fun remove(profileId: String, productId: String) =
        decreaseQuantity.invoke(
            viewModelScope,
            Pair(profileId, productId),
            onException
        ) { product ->
            updateState { it.copy(products = it.products.map { item -> if (item.id == product.id) product else item }) }
        }
}