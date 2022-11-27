package com.numq.fooddeliveryclient.wrapper

import arrow.core.Either
import arrow.core.flatten
import arrow.core.left
import arrow.core.right

fun <T> T.wrap(): Either<Exception, T> =
    runCatching { this }
        .fold(
            onSuccess = { it.right() },
            onFailure = { Exception(it.message).left() }
        )

fun <T> T.wrapIf(condition: Boolean, exception: Exception): Either<Exception, T> =
    Either.conditionally(
        condition,
        ifFalse = { exception },
        ifTrue = { wrap() }
    ).flatten()
