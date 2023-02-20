package com.numq.ecommerce.category

data class CategoryState(
    val categories: List<Category> = listOf(),
    val skip: Long = 0L,
    val limit: Long = 20L
)