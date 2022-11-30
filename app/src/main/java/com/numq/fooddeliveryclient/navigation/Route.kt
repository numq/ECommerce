package com.numq.fooddeliveryclient.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavArgs

sealed class Route private constructor(
    val name: String,
    val icon: ImageVector? = null,
    val destination: String = name.lowercase(),
    val args: NavArgs? = null
) {
    object Auth : Route("auth")
    object Root : Route("root/id")
    object Catalog : Route("catalog")
    object Category : Route("category")
    object Cart : Route("cart")
    object Profile : Route("profile")
}