package com.numq.ecommerce.cart

import com.numq.ecommerce.product.Product

data class CartState(val products: List<Product> = emptyList())