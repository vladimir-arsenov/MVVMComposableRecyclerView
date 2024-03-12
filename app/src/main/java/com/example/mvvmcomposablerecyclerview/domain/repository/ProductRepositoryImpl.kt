package com.example.mvvmcomposablerecyclerview.domain.repository

import com.example.mvvmcomposablerecyclerview.data.model.Product
import com.example.mvvmcomposablerecyclerview.data.model.ProductDetails
import com.example.mvvmcomposablerecyclerview.network.ProductRetrofitService

class ProductRepositoryImpl (
    private val productService: ProductRetrofitService
): ProductRepository {
    override suspend fun loadProducts(skip: Int): List<Product> {
        return productService.getProducts(skip).products
    }

    override suspend fun searchProduct(q: String): List<Product> {
        return productService.searchProduct(q).products
    }

    override suspend fun productDetails(id: Int): ProductDetails {
        return productService.getProductDetails(id)
    }
}