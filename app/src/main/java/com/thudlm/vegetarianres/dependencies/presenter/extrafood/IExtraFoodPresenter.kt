package com.thudlm.vegetarianres.dependencies.presenter.extrafood

import com.thudlm.vegetarianres.dependencies.entities.ExtraFood

interface IExtraFoodPresenter {

    suspend fun insert(extraFood: ExtraFood)

    suspend fun update(extraFood: ExtraFood)

    suspend fun delete(extraFood: ExtraFood)

    suspend fun deleteAll()

    fun loadExtraFoods()
}