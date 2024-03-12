package com.example.mvvmcomposablerecyclerview.network

import com.example.mvvmcomposablerecyclerview.data.model.ProductDetails
import com.example.mvvmcomposablerecyclerview.data.model.ProductsNetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductRetrofitService {
    @GET("/products?limit=20")
    suspend fun getProducts(@Query("skip") skip: Int): ProductsNetworkResponse

    @GET("/products/search")
    suspend fun searchProduct(@Query("q") q: String): ProductsNetworkResponse

    @GET("/products/{id}")
    suspend fun getProductDetails(@Path("id") id: Int): ProductDetails
}