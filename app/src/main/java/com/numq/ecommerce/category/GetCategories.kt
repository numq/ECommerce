package com.numq.ecommerce.category

import arrow.core.flatMap
import arrow.core.right
import com.numq.ecommerce.interactor.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategories @Inject constructor(
    private val repository: CategoryRepository
) : UseCase<Pair<Long, Long>, Flow<Category>>() {
    override suspend fun execute(arg: Pair<Long, Long>) =
        arg.right().flatMap { (skip, limit) ->
            repository.getCategories(skip, limit)
        }
}