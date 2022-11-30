package com.numq.fooddeliveryclient.category

import arrow.core.Either
import arrow.core.flatMap
import com.numq.fooddeliveryclient.extension.tryCatch
import com.numq.fooddeliveryclient.wrapper.wrap
import javax.inject.Inject
import javax.inject.Singleton

interface CategoryRepository {
    suspend fun getCategories(): Either<Exception, List<Category>>

    @Singleton
    class Implementation @Inject constructor(
        private val service: CategoryService
    ) : CategoryRepository {
        override suspend fun getCategories() =
            service.getCategories().wrap()
                .flatMap { Either.tryCatch { it.categoriesList.map { it.entity } } }
    }
}

