package com.numq.ecommerce.cart

import cart.Cart.GetCartRequest
import cart.CartServiceGrpcKt
import io.grpc.ManagedChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

interface CartService {
    suspend fun getCart(id: String): Flow<CartMember>
    suspend fun clearCart(id: String): String
    suspend fun increaseQuantity(id: String, memberId: String): CartMember
    suspend fun decreaseQuantity(id: String, memberId: String): CartMember

    class Implementation @Inject constructor(
        private val channel: ManagedChannel
    ) : CartService {

        private val client by lazy {
            CartServiceGrpcKt.CartServiceCoroutineStub(channel)
        }

        override suspend fun getCart(id: String) = client.getCart(
            GetCartRequest.newBuilder().setCartId(id).build()
        ).itemsList.map { it.entity }.asFlow()

        override suspend fun clearCart(id: String): String =
            client.clearCart(cart.Cart.ClearCartRequest.newBuilder().setCartId(id).build()).cartId

        override suspend fun increaseQuantity(id: String, memberId: String) =
            client.increaseItemQuantity(
                cart.Cart.IncreaseItemQuantityRequest.newBuilder().setCartId(id).build()
            ).item.entity

        override suspend fun decreaseQuantity(id: String, memberId: String) =
            client.decreaseItemQuantity(
                cart.Cart.DecreaseItemQuantityRequest.newBuilder()
                    .setCartId(id)
                    .setItemId(memberId)
                    .build()
            ).item.entity
    }
}