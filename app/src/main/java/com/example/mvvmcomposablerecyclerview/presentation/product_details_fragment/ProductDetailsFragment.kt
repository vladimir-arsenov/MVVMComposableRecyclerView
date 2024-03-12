package com.example.mvvmcomposablerecyclerview.presentation.product_details_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mvvmcomposablerecyclerview.R
import com.example.mvvmcomposablerecyclerview.presentation.components.ProductDetail
import com.example.mvvmcomposablerecyclerview.utils.loadPicture
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private val viewModel: ProductDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("productId")?.let { pId -> viewModel.loadProductDetails(pId) }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                val product = viewModel.product.value
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    product?.thumbnail?.let { url ->
                        val image = loadPicture(url = url, defaultImage = R.drawable.card_loading).value
                        image?.let { img ->
                            Image(
                                painter = BitmapPainter(img.asImageBitmap()),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(250.dp).fillMaxWidth(),
                                contentScale = ContentScale.Crop,
                            )
                        }

                        Text(
                            text = product.title,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        ProductDetail(title = "Price", value = product.price)
                        ProductDetail(title = "Description", value = product.description)
                        ProductDetail(title = "Category", value = product.category)
                        ProductDetail(title = "Brand", value = product.brand)

                    }
                }
            }
        }

    }
}