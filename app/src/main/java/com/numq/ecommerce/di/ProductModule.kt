package com.numq.ecommerce.di

import com.numq.ecommerce.product.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ProductModule {
    @Singleton
    @Binds
    fun bindProductRepository(repository: ProductRepository.Implementation): ProductRepository
}