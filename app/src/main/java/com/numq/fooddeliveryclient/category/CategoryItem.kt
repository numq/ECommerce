package com.numq.fooddeliveryclient.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
fun CategoryItem(category: Category, onClick: (Category) -> Unit) {
    var imageSize by remember {
        mutableStateOf(IntSize.Zero)
    }
    Card(Modifier.clickable { onClick(category) }, elevation = 8.dp) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(category.name)
            Divider()
            Box(modifier = Modifier
                .weight(1f)
                .onSizeChanged {
                    imageSize = it
                })
            category.imageBytes.toImageBitmap(height = imageSize.height, width = imageSize.width)
                ?.let { bitmap ->
                    Image(bitmap = bitmap, contentDescription = "category image")
                } ?: CircularProgressIndicator()
        }
    }
}