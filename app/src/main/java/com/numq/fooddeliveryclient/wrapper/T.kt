package com.numq.fooddeliveryclient.wrapper

import arrow.core.Either
import arrow.core.flatten

fun <T> T.wrap(): Either<Exception, T> = Either.catch { this }.mapLeft { Exception(it.message) }

fun <T> T.wrapIf(condition: Boolean, exception: Exception): Either<Exception, T> =
    Either.conditionally(
        condition,
        ifFalse = { exception },
        ifTrue = { wrap() }
    ).flatten()
