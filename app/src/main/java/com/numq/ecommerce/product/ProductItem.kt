package com.numq.ecommerce.product

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProductItem(product: Product, add: () -> Unit, remove: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        ) {
            runCatching {
                AsyncImage(product.imageBytes, "product image")
            }.getOrNull() ?: Icon(Icons.Rounded.Error, "product")
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(product.name, textAlign = TextAlign.Center)
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                remove()
            }) {
                Icon(Icons.Rounded.Remove, "remove product from cart")
            }
            Text("${product.quantityInCart.coerceAtLeast(0)}")
            IconButton(onClick = {
                add()
            }) {
                Icon(Icons.Rounded.Add, "add product to cart")
            }
        }
    }
}