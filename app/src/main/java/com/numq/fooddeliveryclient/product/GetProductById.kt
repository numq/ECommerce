package com.numq.fooddeliveryclient.product

import com.numq.fooddeliveryclient.interactor.UseCase
import javax.inject.Inject

class GetProductById @Inject constructor(
    private val productRepository: ProductRepository
) : UseCase<String, Product>() {
    override suspend fun execute(arg: String) = productRepository.getProductById(arg)
}