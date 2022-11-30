package com.numq.fooddeliveryclient.category

import com.numq.fooddeliveryclient.product.Product

data class CategoryState(val categoryId: String? = null, val products: List<Product>? = null)