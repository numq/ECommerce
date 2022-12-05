package com.numq.fooddeliveryclient.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.LocalShipping
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CartScreen(vm: CartViewModel = hiltViewModel(), navigateUp: () -> Unit) {

    val state by vm.state.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                4.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            items(state.items) { item ->
                CartItem(
                    Modifier.fillMaxHeight(.5f),
                    item,
                    onItemQuantityDecrease = vm::decreaseItemQuantity,
                    onItemQuantityIncrease = vm::increaseItemQuantity
                )
            }
        }
        state.deliveryPrice?.let {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Delivery price:$it")
                Icon(Icons.Rounded.LocalShipping, "delivery")
            }
        }
        Column(
            Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.CenterVertically)
        ) {
            val (promoCodeValue, setPromoCodeValue) = remember {
                mutableStateOf("")
            }
            Text("Enter promo code:")
            TextField(promoCodeValue, setPromoCodeValue, trailingIcon = {
                // TODO: if promoCode is valid
                if (promoCodeValue == state.promoCode) {
                    Icon(Icons.Rounded.Done, "promo is valid", tint = Color.Green)
                } else {
                    Icon(Icons.Rounded.Clear, "promo is invalid", tint = Color.Red)
                }
            })
        }
        Card {
            Row(
                Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("CHECKOUT")
                Icon(Icons.Rounded.ShoppingCart, "checkout order")
            }
        }
    }
}