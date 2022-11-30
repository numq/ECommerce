package com.numq.fooddeliveryclient.category

import com.numq.fooddeliveryclient.interactor.UseCase
import javax.inject.Inject

class GetCategories @Inject constructor(
    private val repository: CategoryRepository
) : UseCase<Unit, List<Category>>() {
    override suspend fun execute(arg: Unit) = repository.getCategories()
}