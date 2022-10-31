package com.numq.fooddeliveryclient.navigation

import androidx.navigation.NavArgs

sealed class Route private constructor(
    val name: String,
    val destination: String = name.lowercase(),
    val args: NavArgs? = null
) {
    object Auth : Route("auth")
    object Catalog : Route("catalog")
    object Category : Route("category")
    object Cart : Route("cart")
}