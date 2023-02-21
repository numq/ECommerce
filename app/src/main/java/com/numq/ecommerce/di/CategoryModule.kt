package com.numq.ecommerce.di

import com.numq.ecommerce.category.CategoryRepository
import com.numq.ecommerce.category.CategoryService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CategoryModule {
    @Singleton
    @Binds
    fun bindCategoryRepository(repository: CategoryRepository.Implementation): CategoryRepository

    @Singleton
    @Binds
    fun bindCategoryService(service: CategoryService.Implementation): CategoryService
}