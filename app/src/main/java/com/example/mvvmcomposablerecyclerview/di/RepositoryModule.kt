package com.example.mvvmcomposablerecyclerview.di

import com.example.mvvmcomposablerecyclerview.domain.repository.ProductRepository
import com.example.mvvmcomposablerecyclerview.domain.repository.ProductRepositoryImpl
import com.example.mvvmcomposablerecyclerview.network.ProductRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(productService: ProductRetrofitService): ProductRepository {
        return ProductRepositoryImpl(productService)
    }
}