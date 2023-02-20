package com.numq.ecommerce.cart

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.numq.ecommerce.product.Product

@Composable
fun CartItem(product: Product, add: () -> Unit, remove: () -> Unit) {
    Card(Modifier.aspectRatio(1f)) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                runCatching {
                    AsyncImage(product.imageBytes, "product image")
                }.getOrNull() ?: Icon(Icons.Rounded.Error, "product")
                Text(product.name)
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    add()
                }) {
                    Icon(Icons.Rounded.Add, "add product")
                }
                IconButton(onClick = {
                    remove()
                }) {
                    Icon(Icons.Rounded.Remove, "remove product")
                }
            }
        }
    }
}