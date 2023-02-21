package com.numq.ecommerce.product

import arrow.core.Either
import arrow.core.flatten
import com.numq.ecommerce.catalog.CatalogService
import com.numq.ecommerce.catalog.product
import com.numq.ecommerce.connection.ConnectionException
import com.numq.ecommerce.connection.ConnectionService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ProductRepository {
    suspend fun getProductById(id: String): Either<Exception, Product>
    suspend fun loadProducts(
        tags: Array<String>,
        skip: Long,
        limit: Long
    ): Either<Exception, Flow<Product>>

    class Implementation @Inject constructor(
        private val connectionService: ConnectionService,
        private val service: CatalogService
    ) : ProductRepository {

        suspend fun <T> call(body: suspend CatalogService.() -> T) =
            Either.conditionally(connectionService.isConnected, ifFalse = {
                ConnectionException
            }, ifTrue = {
                Either.catch {
                    body(service)
                }.mapLeft(::Exception)
            }).flatten()

        override suspend fun getProductById(id: String) = call {
            getProductById(id).product
        }

        override suspend fun loadProducts(tags: Array<String>, skip: Long, limit: Long) = call {
            service.loadProducts(tags, skip, limit).map { it.product }
        }
    }
}