package com.numq.fooddeliveryclient.product

import ProductOuterClass
import ProductServiceGrpcKt
import io.grpc.ManagedChannel
import javax.inject.Inject
import javax.inject.Singleton

interface ProductService {
    suspend fun getProductById(id: String): ProductOuterClass.GetProductByIdResponse
    suspend fun getProductsFromCategory(
        categoryId: String,
        skip: Long,
        limit: Long
    ): ProductOuterClass.GetProductsFromCategoryResponse

    @Singleton
    class Implementation @Inject constructor(
        channel: ManagedChannel
    ) : ProductService {

        private val stub by lazy {
            ProductServiceGrpcKt.ProductServiceCoroutineStub(channel).withWaitForReady()
        }

        override suspend fun getProductById(id: String) =
            stub.getProductById(
                ProductOuterClass.GetProductByIdRequest.newBuilder().setId(id).build()
            )

        override suspend fun getProductsFromCategory(categoryId: String, skip: Long, limit: Long) =
            stub.getProductsFromCategory(
                ProductOuterClass.GetProductsFromCategoryRequest.newBuilder()
                    .setCategoryId(categoryId).setSkip(skip.toInt()).setLimit(limit.toInt()).build()
            )
    }
}