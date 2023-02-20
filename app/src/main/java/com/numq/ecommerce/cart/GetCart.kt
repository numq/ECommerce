package com.numq.ecommerce.cart

import arrow.core.flatMap
import arrow.core.right
import com.numq.ecommerce.interactor.UseCase
import com.numq.ecommerce.product.Product
import com.numq.ecommerce.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCart @Inject constructor(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository
) : UseCase<String, Flow<Product>>() {
    override suspend fun execute(arg: String) = cartRepository.getCart(arg).flatMap { flow ->
        flow.map { (id, quantity) ->
            productRepository.getProductById(id).orNull()?.copy(quantityInCart = quantity)
        }.filterNotNull().right()
    }
}