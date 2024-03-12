package com.example.mvvmcomposablerecyclerview.presentation.product_list_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.mvvmcomposablerecyclerview.R
import com.example.mvvmcomposablerecyclerview.presentation.components.CircularProgressBar
import com.example.mvvmcomposablerecyclerview.presentation.components.ProductCard
import com.example.mvvmcomposablerecyclerview.presentation.components.SearchBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {
    private val viewModel: ProductListViewModel by viewModels()

    @OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                val products = viewModel.products.value
                val query = viewModel.query.value
                val loading = viewModel.loading.value
                val skip = viewModel.skip.value

                Column {
                    SearchBar(
                        query = query,
                        onQueryChanged = viewModel::onQueryChanged,
                        searchProduct = viewModel::searchProduct,
                        keyboardController = LocalSoftwareKeyboardController.current
                    )

                    Box(modifier = Modifier.fillMaxSize()) {
                        LazyColumn(modifier = Modifier.padding(16.dp)) {
                            itemsIndexed(items = products) { index, product ->
                                viewModel.onChangedListScrollPosition(index)
                                if (index + 1 >= skip && !loading)
                                    viewModel.loadNextPage()

                                ProductCard(
                                    product = product,
                                    onClick = {
                                        val bundle = Bundle()
                                        bundle.putInt("productId", product.id)
                                        findNavController().navigate(R.id.viewProductDetails, bundle)
                                    }
                                )
                            }
                        }
                        CircularProgressBar(isDisplayed = loading)

                    }
                }
            }
        }
    }
}