package com.example.mvvmcomposablerecyclerview.presentation.product_list_fragment

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcomposablerecyclerview.data.model.Product
import com.example.mvvmcomposablerecyclerview.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor( private val repository: ProductRepository): ViewModel() {
    val products: MutableState<List<Product>> = mutableStateOf(listOf())
    val query = mutableStateOf("")
    val loading = mutableStateOf(false)
    val skip = mutableStateOf(0)
    private var productListScrollPosition = 0
    init{
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            loading.value = true
            products.value = repository.loadProducts(0)
            loading.value = false
        }
    }

    fun loadNextPage() {
        if (query.value == "") { // do not load next page when search query is made
            viewModelScope.launch {
                if (productListScrollPosition + 1 >= skip.value) {
                    loading.value = true
                    incrementSkip()
                    if (skip.value > 0) {
                        val result = ArrayList<Product>(repository.loadProducts(skip.value))
                        result.addAll(0, products.value)
                        products.value = result
                    }
                    loading.value = false
                }
            }
        }
    }

    fun searchProduct() {
        resetScrollPosition()
        if (query.value == "")
            loadProducts()
        else
            viewModelScope.launch {
                loading.value = true
                products.value = repository.searchProduct(query.value)
                loading.value = false
            }
    }

    fun onQueryChanged(query: String){ this.query.value = query }

    fun onChangedListScrollPosition(position: Int) { productListScrollPosition = position }

    private fun incrementSkip() { skip.value += 20 }

    private fun resetScrollPosition() {
        skip.value = 0
        onChangedListScrollPosition(0)
        products.value = listOf()
    }
}