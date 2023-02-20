package com.numq.ecommerce.cart

import androidx.lifecycle.viewModelScope
import com.numq.ecommerce.viewmodel.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCart: GetCart,
    private val clearCart: ClearCart,
    private val increaseQuantity: IncreaseQuantity,
    private val decreaseQuantity: DecreaseQuantity
) : StateViewModel<CartState>(CartState()) {
    fun getCart(profileId: String) =
        getCart.invoke(viewModelScope, profileId, onException) { flow ->
            viewModelScope.launch {
                val products = flow.toList()
                updateState { it.copy(products = products) }
            }
        }

    fun clear(profileId: String) = clearCart.invoke(viewModelScope, profileId, onException) {
        updateState { it.copy(products = emptyList()) }
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