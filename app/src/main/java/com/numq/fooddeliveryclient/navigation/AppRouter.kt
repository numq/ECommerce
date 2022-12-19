package com.numq.fooddeliveryclient.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocalShipping
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.numq.fooddeliveryclient.auth.AuthScreen
import com.numq.fooddeliveryclient.cart.CartScreen
import com.numq.fooddeliveryclient.catalog.CatalogScreen
import com.numq.fooddeliveryclient.category.CategoryScreen
import com.numq.fooddeliveryclient.extension.rememberDataStore
import com.numq.fooddeliveryclient.permission.PermissionsRequired
import com.numq.fooddeliveryclient.profile.ProfileScreen
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

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
        val dataStore = rememberDataStore()

        val dataFlow = dataStore.data.collectAsState(null)
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            withTimeout(5000) {
                dataStore.edit {
                    it[stringPreferencesKey("test")] = "test"
                }
            }
        }

        LaunchedEffect(dataFlow) {
            Log.e(javaClass.simpleName, dataFlow.value?.toString() ?: "Empty preferences")
        }

        val state by vm.state.collectAsState()
        Scaffold(Modifier.fillMaxSize(), floatingActionButton = {
            if (navController.currentDestination?.route in listOf(
                    Route.Catalog.destination,
                    Route.Category.destination
                )
            ) {
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
            }
        }, scaffoldState = scaffoldState) { paddingValues ->
            NavHost(
                navController,
                startDestination = Route.Auth.destination,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                composable(route = Route.Auth.destination) {
                    AuthScreen(scaffoldState, navigateToCatalog = { tokenPair ->
                        coroutineScope.launch {
                            dataStore.edit {
                                it[stringPreferencesKey("refreshToken")] = tokenPair.refreshToken
                                it[stringPreferencesKey("accessToken")] = tokenPair.accessToken
                            }
                            Log.e("DATASTORE", dataStore.data.last().toString())
                        }
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