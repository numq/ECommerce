package com.numq.ecommerce.product

import arrow.core.flatMap
import arrow.core.right
import com.numq.ecommerce.interactor.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProducts @Inject constructor(
    private val repository: ProductRepository
) : UseCase<Triple<Array<String>, Long, Long>, Flow<Product>>() {
    override suspend fun execute(arg: Triple<Array<String>, Long, Long>) =
        arg.right().flatMap { (tags, skip, limit) ->
            repository.loadProducts(tags, skip, limit)
        }
}