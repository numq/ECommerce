package com.numq.ecommerce.cart

import com.numq.ecommerce.interactor.UseCase
import javax.inject.Inject

class ClearCart @Inject constructor(
    private val repository: CartRepository
) : UseCase<String, Unit>() {
    override suspend fun execute(arg: String) = repository.clearCart(arg)
}