package com.numq.fooddeliveryclient.extension

import arrow.core.Either

fun <T> Either.Companion.tryCatch(f: () -> T): Either<Exception, T> =
    catch({ Exception(it.message) }, { f() })