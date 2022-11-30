package com.numq.fooddeliveryclient.product

val ProductOuterClass.Product.entity: Product
    get() = Product(
        id,
        name,
        description,
        imageBytes.toByteArray(),
        price,
        discount,
        weight,
        quantity,
        categoryId,
        tagsList,
        createdAt,
        updatedAt
    )

val Product.message: ProductOuterClass.Product
    get() = ProductOuterClass.Product.newBuilder().run {
        id = id
        name = name
        description = description
        imageBytes = imageBytes
        price = price
        discount = discount
        weight = weight
        quantity = quantity
        categoryId = categoryId
        tags.forEachIndexed(::setTags)
        createdAt = createdAt
        updatedAt = updatedAt
        build()
    }