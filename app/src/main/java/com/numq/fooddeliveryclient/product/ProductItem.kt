package com.numq.fooddeliveryclient.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.numq.fooddeliveryclient.extension.toImageBitmap

@Composable
fun ProductItem(product: Product) {
    var imageSize by remember {
        mutableStateOf(IntSize.Zero)
    }
    Card(elevation = 8.dp) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(product.name)
            Divider()
            Box(modifier = Modifier
                .weight(1f)
                .onSizeChanged {
                    imageSize = it
                })
            product.imageBytes.toImageBitmap(height = imageSize.height, width = imageSize.width)
                ?.let { bitmap ->
                    Image(bitmap = bitmap, contentDescription = "product image")
                } ?: CircularProgressIndicator()
        }
    }
}