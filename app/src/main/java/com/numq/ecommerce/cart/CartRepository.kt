package com.numq.ecommerce.cart

import arrow.core.Either
import arrow.core.flatten
import com.numq.ecommerce.connection.ConnectionException
import com.numq.ecommerce.connection.ConnectionService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CartRepository {
    suspend fun getCart(id: String): Either<Exception, Flow<CartMember>>
    suspend fun clearCart(id: String): Either<Exception, String>
    suspend fun increaseQuantity(id: String, memberId: String): Either<Exception, CartMember>
    suspend fun decreaseQuantity(id: String, memberId: String): Either<Exception, CartMember>

    class Implementation @Inject constructor(
        private val connectionService: ConnectionService,
        private val service: CartService
    ) : CartRepository {

        suspend fun <T> call(body: suspend CartService.() -> T) =
            Either.conditionally(connectionService.isConnected, ifFalse = {
                ConnectionException
            }, ifTrue = {
                Either.catch {
                    body(service)
                }.mapLeft(::Exception)
            }).flatten()

        override suspend fun getCart(id: String) = call {
            getCart(id)
        }

        override suspend fun clearCart(id: String) = call {
            clearCart(id)
        }

        override suspend fun increaseQuantity(id: String, memberId: String) = call {
            increaseQuantity(id, memberId)
        }

        override suspend fun decreaseQuantity(id: String, memberId: String) = call {
            decreaseQuantity(id, memberId)
        }
    }
}