package com.numq.fooddeliveryclient.navigation

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.numq.fooddeliveryclient.auth.AuthScreen
import com.numq.fooddeliveryclient.cart.CartScreen
import com.numq.fooddeliveryclient.catalog.CatalogScreen
import com.numq.fooddeliveryclient.category.CategoryScreen

@Composable
fun AppRouter() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavHost(navController, startDestination = Route.Auth.destination) {
        composable(route = Route.Auth.destination) {
            AuthScreen() {

            }
        }
        composable(route = Route.Catalog.destination) {
            CatalogScreen() {

            }
        }
        composable(route = Route.Category.destination) {
            CategoryScreen() {

            }
        }
        composable(route = Route.Cart.destination) {
            CartScreen() {

            }
        }
    }
}