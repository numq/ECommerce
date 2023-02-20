package com.numq.ecommerce.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage

@Composable
fun CategoryItem(category: Category, openCategory: (Category) -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            Modifier
                .aspectRatio(1f)
                .clickable { openCategory(category) }) {
            runCatching {
                AsyncImage(category.imageBytes, "category image")
            }.getOrNull() ?: Icon(Icons.Rounded.Error, "category")
        }
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(category.name, textAlign = TextAlign.Center)
        }
    }
}