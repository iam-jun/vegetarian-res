package com.thudlm.vegetarianres.dependencies.presenter.category

import com.thudlm.vegetarianres.dependencies.entities.Category

interface ICategoryPresenter {

    suspend fun insert(category: Category)

    suspend fun update(category: Category)

    suspend fun delete(category: Category)

    suspend fun deleteAll()

    fun loadCategory()
}