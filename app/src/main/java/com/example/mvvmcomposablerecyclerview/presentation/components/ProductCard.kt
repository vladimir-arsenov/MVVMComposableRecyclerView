package com.example.mvvmcomposablerecyclerview.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mvvmcomposablerecyclerview.R
import com.example.mvvmcomposablerecyclerview.data.model.Product
import com.example.mvvmcomposablerecyclerview.utils.loadPicture

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit
){
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(bottom = 6.dp, top = 6.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        ) {

        Column() {
            val image =
                loadPicture(url = product.thumbnail, defaultImage = R.drawable.card_loading).value
            image?.let { img ->
                Image(
                    painter = BitmapPainter(img.asImageBitmap()),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(225.dp),
                    contentScale = ContentScale.Crop,
                )
            }
            product.title?.let {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
                ) {
                    Row {
                        Text(
                            text = product.title,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .wrapContentWidth(Alignment.Start),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(
                            text = "${product.price} $",
                            modifier = Modifier
                                .wrapContentWidth(Alignment.Start)
                                .align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Text(
                        text = product.description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}