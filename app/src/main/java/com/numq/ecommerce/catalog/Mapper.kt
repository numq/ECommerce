package com.numq.ecommerce.catalog

import com.numq.ecommerce.product.Product

val catalog.Catalog.CatalogItem.entity
    get() = CatalogItem(
        id,
        sku,
        name,
        description,
        imageBytes.toByteArray(),
        price,
        discount,
        weight,
        quantity,
        tagsList.toTypedArray(),
        createdAt,
        updatedAt
    )

val CatalogItem.product
    get() = Product(
        id,
        sku,
        name,
        description,
        imageBytes,
        price,
        discount,
        weight,
        quantity,
        0,
        tags,
        createdAt,
        updatedAt
    )