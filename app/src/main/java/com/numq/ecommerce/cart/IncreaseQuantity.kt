package com.numq.ecommerce.cart

import arrow.core.flatMap
import arrow.core.right
import com.numq.ecommerce.interactor.UseCase
import com.numq.ecommerce.product.Product
import com.numq.ecommerce.product.ProductRepository
import javax.inject.Inject

class IncreaseQuantity @Inject constructor(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository
) : UseCase<Pair<String, String>, Product>() {
    override suspend fun execute(arg: Pair<String, String>) =
        arg.right().flatMap { (cartId, itemId) ->
            cartRepository.increaseQuantity(cartId, itemId)
        }.flatMap { (id, quantity) ->
            productRepository.getProductById(id).map { quantity to it }
        }.map { (quantity, product) -> product.copy(quantityInCart = quantity) }
}