package com.numq.ecommerce.di

import com.numq.ecommerce.cart.CartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CartModule {
    @Singleton
    @Binds
    fun bindCartRepository(repository: CartRepository.Implementation): CartRepository
}