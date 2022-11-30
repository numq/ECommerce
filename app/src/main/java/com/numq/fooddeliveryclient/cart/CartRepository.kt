package com.numq.fooddeliveryclient.cart

import javax.inject.Inject

interface CartRepository {
    class Implementation @Inject constructor(
        private val api: CartApi
    ) : CartRepository {

    }
}