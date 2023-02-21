package com.numq.ecommerce.cart

import cart.Cart.Item

val Item.entity
    get() = CartMember(id, quantity, addedAt)