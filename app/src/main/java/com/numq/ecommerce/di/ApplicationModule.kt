package com.numq.ecommerce.di

import android.content.Context
import com.numq.ecommerce.config.Configuration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.grpc.android.AndroidChannelBuilder
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideGatewayChannel(@ApplicationContext context: Context) = AndroidChannelBuilder
        .forAddress(Configuration.GATEWAY_HOSTNAME, Configuration.GATEWAY_PORT)
        .context(context)
        .usePlaintext()
        .build()

}