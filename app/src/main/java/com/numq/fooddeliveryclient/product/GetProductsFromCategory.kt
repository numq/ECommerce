package com.numq.fooddeliveryclient.product

import com.numq.fooddeliveryclient.interactor.UseCase
import javax.inject.Inject

class GetProductsFromCategory @Inject constructor(
    private val repository: ProductRepository
) : UseCase<Triple<String, Long, Long>, List<Product>>() {
    override suspend fun execute(arg: Triple<String, Long, Long>) =
        arg.let { (id, skip, limit) -> repository.getProductsFromCategory(id, skip, limit) }
}