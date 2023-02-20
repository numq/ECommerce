package com.numq.ecommerce.category

import arrow.core.Either
import arrow.core.right
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

interface CategoryRepository {
    fun getCategories(skip: Long, limit: Long): Either<Exception, Flow<Category>>

    class Implementation @Inject constructor(

    ) : CategoryRepository {
        override fun getCategories(skip: Long, limit: Long): Either<Exception, Flow<Category>> {

            val category1 = Category(
                id = "cat001",
                name = "Electronics",
                description = "Category for electronic devices",
                imageBytes = byteArrayOf(
                    0x89.toByte(),
                    0x50.toByte(),
                    0x4E.toByte(),
                    0x47.toByte()
                ), // example PNG image bytes
                tags = arrayOf("electronics", "devices", "technology"),
                createdAt = 1644547200, // February 11, 2022 12:00:00 AM UTC
                updatedAt = 1645557600 // February 23, 2022 12:00:00 AM UTC
            )

            val category2 = Category(
                id = "cat002",
                name = "Clothing",
                description = "Category for clothing and fashion items",
                imageBytes = byteArrayOf(
                    0xFF.toByte(),
                    0xD8.toByte(),
                    0xFF.toByte(),
                    0xE0.toByte()
                ), // example JPEG image bytes
                tags = arrayOf("clothing", "fashion", "style"),
                createdAt = 1644633600, // February 12, 2022 12:00:00 AM UTC
                updatedAt = 1645644000 // February 24, 2022 12:00:00 AM UTC
            )

            val category3 = Category(
                id = "cat003",
                name = "Home and Garden",
                description = "Category for home and garden products",
                imageBytes = byteArrayOf(
                    0x47.toByte(),
                    0x49.toByte(),
                    0x46.toByte(),
                    0x38.toByte()
                ), // example GIF image bytes
                tags = arrayOf("home", "garden", "products"),
                createdAt = 1644720000, // February 13, 2022 12:00:00 AM UTC
                updatedAt = 1645730400 // February 25, 2022 12:00:00 AM UTC
            )

            val mockCategoryList = listOf(category1, category2, category3)

            return mockCategoryList.asFlow().right()
        }
    }
}