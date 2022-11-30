package com.numq.fooddeliveryclient.category

import CategoryOuterClass

val CategoryOuterClass.Category.entity: Category
    get() = Category(id, name, description, imageBytes.toByteArray(), createdAt, updatedAt)

val Category.message: CategoryOuterClass.Category
    get() = CategoryOuterClass.Category.newBuilder().run {
        id = id
        name = name
        description = description
        imageBytes = imageBytes
        createdAt = createdAt
        updatedAt = updatedAt
        build()
    }