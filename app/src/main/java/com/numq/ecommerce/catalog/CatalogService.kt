package com.numq.ecommerce.catalog

import catalog.Catalog.GetCatalogItemByIdRequest
import catalog.Catalog.GetCatalogItemsByTagsRequest
import catalog.CatalogServiceGrpcKt
import io.grpc.ManagedChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

interface CatalogService {
    suspend fun getProductById(id: String): CatalogItem
    suspend fun loadProducts(
        tags: Array<String>,
        skip: Long,
        limit: Long
    ): Flow<CatalogItem>

    class Implementation @Inject constructor(
        private val channel: ManagedChannel
    ) : CatalogService {

        private val client by lazy { CatalogServiceGrpcKt.CatalogServiceCoroutineStub(channel) }

        override suspend fun getProductById(id: String) = client.getCatalogItemById(
            GetCatalogItemByIdRequest.newBuilder().setId(id).build()
        ).item.entity

        override suspend fun loadProducts(
            tags: Array<String>,
            skip: Long,
            limit: Long
        ) = client.getCatalogItemsByTags(
            GetCatalogItemsByTagsRequest.newBuilder()
                .addAllTags(tags.toList())
                .setSkip(skip)
                .setLimit(limit)
                .build()
        ).itemsList.map { it.entity }.asFlow()
    }
}