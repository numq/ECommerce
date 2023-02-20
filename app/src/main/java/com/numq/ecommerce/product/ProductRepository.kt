package com.numq.ecommerce.product

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface ProductRepository {
    fun getProductById(id: String): Either<Exception, Product>
    fun loadProducts(
        tags: Array<String>,
        skip: Long,
        limit: Long
    ): Either<Exception, Flow<Product>>

    class Implementation @Inject constructor(

    ) : ProductRepository {
        override fun getProductById(id: String): Either<Exception, Product> {

            return Exception().left()
        }

        override fun loadProducts(
            tags: Array<String>,
            skip: Long,
            limit: Long
        ): Either<Exception, Flow<Product>> {

// Create mock products for the Electronics category
            val electronicsProduct1 = Product(
                id = "elec001",
                sku = "EL001",
                name = "Apple iPhone 13 Pro",
                description = "The latest iPhone from Apple",
                imageBytes = byteArrayOf(
                    0xFF.toByte(),
                    0xD8.toByte(),
                    0xFF.toByte(),
                    0xE0.toByte()
                ), // example JPEG image bytes
                price = 999.99f,
                discount = 0f,
                weight = 0.2f,
                quantityInStock = 50,
                quantityInCart = 0,
                tags = arrayOf("iphone", "apple", "smartphone", "electronic"),
                createdAt = 1644547200, // February 11, 2022 12:00:00 AM UTC
                updatedAt = 1645557600 // February 23, 2022 12:00:00 AM UTC
            )

            val electronicsProduct2 = Product(
                id = "elec002",
                sku = "EL002",
                name = "Samsung Galaxy S21",
                description = "The latest Samsung phone",
                imageBytes = byteArrayOf(
                    0x89.toByte(),
                    0x50.toByte(),
                    0x4E.toByte(),
                    0x47.toByte()
                ), // example PNG image bytes
                price = 899.99f,
                discount = 0.1f,
                weight = 0.25f,
                quantityInStock = 30,
                quantityInCart = 0,
                tags = arrayOf("samsung", "smartphone", "electronic"),
                createdAt = 1644633600, // February 12, 2022 12:00:00 AM UTC
                updatedAt = 1645644000 // February 24, 2022 12:00:00 AM UTC
            )

// Create mock products for the Clothing category
            val clothingProduct1 = Product(
                id = "clot001",
                sku = "CL001",
                name = "Levi's 501 Original Fit Jeans",
                description = "Classic straight-leg jeans from Levi's",
                imageBytes = byteArrayOf(
                    0x47.toByte(),
                    0x49.toByte(),
                    0x46.toByte(),
                    0x38.toByte()
                ), // example GIF image bytes
                price = 69.99f,
                discount = 0.2f,
                weight = 0.6f,
                quantityInStock = 100,
                quantityInCart = 0,
                tags = arrayOf("clothing", "jeans", "levis"),
                createdAt = 1644720000, // February 13, 2022 12:00:00 AM UTC
                updatedAt = 1645730400 // February 25, 2022 12:00:00 AM UTC
            )

            val clothingProduct2 = Product(
                id = "clot002",
                sku = "CL002",
                name = "Nike Air Force 1 '07",
                description = "Classic low-top sneakers from Nike",
                imageBytes = byteArrayOf(
                    0xFF.toByte(),
                    0xD8.toByte(),
                    0xFF.toByte(),
                    0xE0.toByte()
                ), // example JPEG image bytes
                price = 89.99f,
                discount = 0.1f,
                weight = 0.4f,
                quantityInStock = 50,
                quantityInCart = 0,
                tags = arrayOf("clothing", "shoes", "nike"),
                createdAt = 1644806400, // February 14, 2022 12:00:00 AM UTC
                updatedAt = 1645816800 // February
            )

            return flowOf(
                electronicsProduct1,
                electronicsProduct2,
                clothingProduct1,
                clothingProduct2
            ).right()
        }
    }
}