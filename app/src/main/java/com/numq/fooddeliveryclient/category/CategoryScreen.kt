package com.numq.fooddeliveryclient.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.numq.fooddeliveryclient.error.ShowError
import com.numq.fooddeliveryclient.product.ProductItem

@Composable
fun CategoryScreen(scaffoldState: ScaffoldState, vm: CategoryViewModel = hiltViewModel()) {
    val state by vm.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize().padding(8.dp), contentAlignment = Alignment.Center) {
        state.products?.let { products ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(products) { product ->
                    ProductItem(product)
                }
            }
        } ?: CircularProgressIndicator()
    }
    vm.exception.collectAsState(null).value?.let { ShowError(scaffoldState, it) }
}