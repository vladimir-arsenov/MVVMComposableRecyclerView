package com.example.mvvmcomposablerecyclerview.presentation.product_details_fragment

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcomposablerecyclerview.data.model.ProductDetails
import com.example.mvvmcomposablerecyclerview.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(private val repository: ProductRepository): ViewModel()  {
    val product: MutableState<ProductDetails?> = mutableStateOf(null)

    fun loadProductDetails(id: Int) {
        viewModelScope.launch {
            product.value = repository.productDetails(id)
        }
    }

}