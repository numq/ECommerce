package com.numq.ecommerce.category

val category.CategoryOuterClass.Category.entity
    get() = Category(
        id,
        name,
        description,
        imageBytes.toByteArray(),
        tagsList.toTypedArray(),
        createdAt,
        updatedAt
    )