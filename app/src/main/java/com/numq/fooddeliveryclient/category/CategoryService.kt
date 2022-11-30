package com.numq.fooddeliveryclient.category

import CategoryOuterClass
import CategoryServiceGrpcKt
import io.grpc.ManagedChannel
import javax.inject.Inject
import javax.inject.Singleton

interface CategoryService {
    suspend fun getCategories(): CategoryOuterClass.GetCategoriesResponse

    @Singleton
    class Implementation @Inject constructor(
        channel: ManagedChannel
    ) : CategoryService {

        private val stub by lazy {
            CategoryServiceGrpcKt.CategoryServiceCoroutineStub(channel).withWaitForReady()
        }

        override suspend fun getCategories() =
            stub.getCategories(CategoryOuterClass.GetCategoriesRequest.newBuilder().build())
    }
}