package com.numq.fooddeliveryclient.product

import arrow.core.Either
import arrow.core.flatMap
import com.numq.fooddeliveryclient.extension.tryCatch
import com.numq.fooddeliveryclient.wrapper.wrap
import javax.inject.Inject
import javax.inject.Singleton

interface ProductRepository {
    suspend fun getProductById(id: String): Either<Exception, Product>
    suspend fun getProductsFromCategory(
        id: String,
        skip: Long,
        limit: Long
    ): Either<Exception, List<Product>>

    @Singleton
    class Implementation @Inject constructor(
        private val service: ProductService
    ) : ProductRepository {
        override suspend fun getProductById(id: String) =
            service.getProductById(id)
                .wrap()
                .flatMap { Either.tryCatch { it.product.entity } }

        override suspend fun getProductsFromCategory(
            id: String,
            skip: Long,
            limit: Long
        ) = service.getProductsFromCategory(id, skip, limit)
            .wrap()
            .flatMap { Either.tryCatch { it.productsList.map { it.entity } } }
    }
}