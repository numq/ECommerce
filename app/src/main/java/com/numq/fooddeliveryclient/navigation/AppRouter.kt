package com.numq.fooddeliveryclient.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocalShipping
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.numq.fooddeliveryclient.auth.AuthScreen
import com.numq.fooddeliveryclient.cart.CartScreen
import com.numq.fooddeliveryclient.catalog.CatalogScreen
import com.numq.fooddeliveryclient.category.CategoryScreen
import com.numq.fooddeliveryclient.permission.PermissionsRequired
import com.numq.fooddeliveryclient.profile.ProfileScreen

@Composable
fun AppRouter(vm: NavViewModel = hiltViewModel()) {

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    val permissions = listOf(
        android.Manifest.permission.INTERNET,
        android.Manifest.permission.ACCESS_NETWORK_STATE,
        android.Manifest.permission.ACCESS_WIFI_STATE
    )

    PermissionsRequired(permissions) {
        val state by vm.state.collectAsState()
        Scaffold(Modifier.fillMaxSize(), floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Route.Cart.destination) {
                    launchSingleTop = true
                }
            }) {
                Icon(
                    if (state.delivering) Icons.Rounded.LocalShipping else Icons.Rounded.ShoppingCart,
                    "cart"
                )
            }
        }, scaffoldState = scaffoldState) { paddingValues ->
            NavHost(
                navController,
                startDestination = Route.Catalog.destination,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                composable(route = Route.Auth.destination) {
                    AuthScreen(navigateToCatalog = {
                        navController.navigate(Route.Catalog.destination) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    })
                }
                composable(route = Route.Catalog.destination) {
                    CatalogScreen(scaffoldState, navigateToCategory = {
//                        it.arguments?.getString("categoryId")?.let { categoryId ->
//                            navController.navigate(Route.Category.destination + "/$categoryId") {
//                                popUpTo(0)
//                            }
//                        }
                    })
                }
                composable(route = Route.Category.destination) {
                    CategoryScreen(scaffoldState)
                }
                composable(route = Route.Cart.destination) {
                    CartScreen(navigateUp = {
                        navController.navigateUp()
                    })
                }
                composable(route = Route.Profile.destination) {
                    ProfileScreen()
                }
            }
        }
    }
}