package com.numq.fooddeliveryclient.catalog

import com.numq.fooddeliveryclient.category.Category

data class CatalogState(val categories: List<Category>? = null)