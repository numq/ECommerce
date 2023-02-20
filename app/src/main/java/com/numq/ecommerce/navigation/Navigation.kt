package com.numq.ecommerce.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.numq.ecommerce.R
import com.numq.ecommerce.authentication.Authentication
import com.numq.ecommerce.cart.Cart
import com.numq.ecommerce.category.Categories
import com.numq.ecommerce.permission.PermissionsRequired
import com.numq.ecommerce.product.Products
import com.numq.ecommerce.profile.Profile

@Composable
fun Navigation(vm: NavigationViewModel = hiltViewModel()) {

    val permissions = listOf<String>()

    PermissionsRequired(permissions) {

        val state by vm.state.collectAsState()

        state.id?.let { id ->

            val navController = rememberNavController()
            val navigationBackStack by navController.currentBackStackEntryAsState()

            Scaffold(Modifier.fillMaxSize(), topBar = {
                TopAppBar(title = {
                    Text(stringResource(R.string.app_name))
                }, actions = {
                    IconButton(onClick = {
                        navController.navigate(Destination.PROFILE.name)
                    }) {
                        Icon(Icons.Rounded.Person, "profile")
                    }
                })
            }, floatingActionButton = {
                if (
                    arrayOf(Destination.CATEGORIES.name, Destination.PRODUCTS.name)
                        .contains(navigationBackStack?.destination?.route)
                ) {
                    FloatingActionButton(onClick = {
                        navController.navigate(Destination.CART.name)
                    }) {
                        Icon(Icons.Rounded.ShoppingCart, "shopping cart")
                    }
                }
            }) { paddingValues ->
                NavHost(
                    navController,
                    startDestination = Destination.CATEGORIES.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    composable(Destination.PROFILE.name) {
                        Profile()
                    }
                    composable(Destination.CATEGORIES.name) {
                        Categories() { category ->
                            val args = bundleOf("tags" to category.tags)
                            navController.findDestination(Destination.PRODUCTS.name)?.id?.let { route ->
                                navController.navigate(route, args)
                            }
                        }
                    }
                    composable(Destination.PRODUCTS.name) {
                        Products(id, it.arguments?.getStringArray("tags") ?: emptyArray())
                    }
                    composable(Destination.CART.name) {
                        Cart(id)
                    }
                }
            }
        } ?: Authentication()
    }
}