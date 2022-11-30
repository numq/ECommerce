package com.numq.fooddeliveryclient.cart

interface CartUseCase {
    fun addMore(id: String)
    fun removeOne()
}