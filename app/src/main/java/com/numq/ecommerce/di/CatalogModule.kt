package com.numq.ecommerce.di

import com.numq.ecommerce.catalog.CatalogService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CatalogModule {
    @Singleton
    @Binds
    fun bindCatalogService(service: CatalogService.Implementation): CatalogService
}