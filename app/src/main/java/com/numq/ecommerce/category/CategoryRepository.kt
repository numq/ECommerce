package com.numq.ecommerce.category

import arrow.core.Either
import arrow.core.flatten
import com.numq.ecommerce.connection.ConnectionException
import com.numq.ecommerce.connection.ConnectionService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CategoryRepository {
    suspend fun getCategories(skip: Long, limit: Long): Either<Exception, Flow<Category>>

    class Implementation @Inject constructor(
        private val connectionService: ConnectionService,
        private val service: CategoryService
    ) : CategoryRepository {

        override suspend fun getCategories(
            skip: Long,
            limit: Long
        ) = Either.conditionally(connectionService.isConnected, ifFalse = {
            ConnectionException
        }, ifTrue = {
            Either.catch {
                service.getCategories()
            }.mapLeft(::Exception)
        }).flatten()
    }
}