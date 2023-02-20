package com.numq.ecommerce.product

data class ProductState(
    val products: List<Product> = emptyList(),
    val skip: Long = 0L,
    val limit: Long = 20L
)