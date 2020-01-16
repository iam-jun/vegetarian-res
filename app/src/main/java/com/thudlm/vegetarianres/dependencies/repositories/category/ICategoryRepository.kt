package com.thudlm.vegetarianres.dependencies.repositories.category

import com.thudlm.vegetarianres.dependencies.entities.Category
import io.reactivex.Single

interface ICategoryRepository {

    suspend fun insert(category: Category)

    suspend fun update(category: Category)

    suspend fun delete(category: Category)

    suspend fun deleteAll()

    fun getAllCategory(): Single<List<Category>>

}