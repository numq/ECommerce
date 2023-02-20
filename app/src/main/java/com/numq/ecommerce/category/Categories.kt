package com.numq.ecommerce.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Categories(vm: CategoryViewModel = hiltViewModel(), openCategory: (Category) -> Unit) {

    val state by vm.state.collectAsState()

    LaunchedEffect(state.skip) {
        vm.getCategories(state.skip, state.limit)
    }

    LazyVerticalGrid(
        columns = if (state.categories.size % 2 == 0) GridCells.Fixed(2) else GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(state.categories) {
            CategoryItem(it, openCategory)
        }
    }
}