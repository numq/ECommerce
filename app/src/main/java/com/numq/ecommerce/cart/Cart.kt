package com.numq.ecommerce.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.ShoppingCartCheckout
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Cart(id: String, vm: CartViewModel = hiltViewModel()) {

    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) {
        vm.getCart(id)
    }

    val (promo, setPromo) = remember {
        mutableStateOf("")
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        Text("There are ${state.products.count()} items in your cart")
                    }
                    IconButton(onClick = {
                        vm.clear(id)
                    }, enabled = state.products.isNotEmpty()) {
                        Icon(Icons.Rounded.Clear, "clear cart")
                    }
                }
                LazyColumn(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    items(state.products) { product ->
                        CartItem(product, add = {
                            vm.add(id, product.id)
                        }, remove = {
                            vm.remove(id, product.id)
                        })
                    }
                }
            }
        }
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(promo, setPromo, modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    // TODO: apply promo
                }, enabled = promo.isNotBlank()) {
                    Icon(Icons.Rounded.Done, "apply promo")
                }
            }
            Spacer(Modifier.height(16.dp))
            Button(onClick = {
                // TODO: checkout
            }, enabled = state.products.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Rounded.ShoppingCartCheckout, "checkout")
                    Text("CHECKOUT")
                }
            }
        }
    }
}