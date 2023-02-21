package com.numq.ecommerce.category

import category.CategoryOuterClass.GetCategoriesRequest
import category.CategoryServiceGrpcKt
import io.grpc.ManagedChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

interface CategoryService {
    suspend fun getCategories(): Flow<Category>

    class Implementation @Inject constructor(
        private val channel: ManagedChannel
    ) : CategoryService {

        private val client by lazy {
            CategoryServiceGrpcKt.CategoryServiceCoroutineStub(channel)
        }

        override suspend fun getCategories() = client.getCategories(
            GetCategoriesRequest.newBuilder().build()
        ).categoriesList.map { it.entity }.asFlow()
    }
}