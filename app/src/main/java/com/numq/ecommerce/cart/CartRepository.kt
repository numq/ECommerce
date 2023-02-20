package com.numq.ecommerce.cart

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface CartRepository {
    fun getCart(id: String): Either<Exception, Flow<CartMember>>
    fun clearCart(id: String): Either<Exception, Unit>
    fun increaseQuantity(id: String, memberId: String): Either<Exception, CartMember>
    fun decreaseQuantity(id: String, memberId: String): Either<Exception, CartMember>

    class Implementation @Inject constructor(

    ) : CartRepository {
        override fun getCart(id: String): Either<Exception, Flow<CartMember>> {

            return flowOf<CartMember>().right()
        }

        override fun clearCart(id: String): Either<Exception, Unit> {

            return Exception().left()
        }

        override fun increaseQuantity(id: String, memberId: String): Either<Exception, CartMember> {

            return Exception().left()
        }

        override fun decreaseQuantity(id: String, memberId: String): Either<Exception, CartMember> {

            return Exception().left()
        }
    }
}