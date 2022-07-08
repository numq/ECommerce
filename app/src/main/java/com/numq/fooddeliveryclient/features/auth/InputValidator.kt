package com.numq.fooddeliveryclient.features.auth

object InputValidator {
    val validateString: (String) -> String = { input -> input.filter { it.isLetterOrDigit() } }
    val emailPattern = Regex("^[\\w\\-.]+@[\\w.]+\\.[\\w]+\$")
    val emailConstraints = 3..32
    val passwordConstraints = 8..32
}