package com.example.mvvmcomposablerecyclerview.domain.repository

import com.example.mvvmcomposablerecyclerview.data.model.Product
import com.example.mvvmcomposablerecyclerview.data.model.ProductDetails

interface ProductRepository {

    suspend fun loadProducts(skip: Int): List<Product>
    suspend fun searchProduct(q: String): List<Product>
    suspend fun productDetails(id: Int): ProductDetails
}