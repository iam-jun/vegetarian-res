package com.thudlm.vegetarianres.dependencies.repositories.extrafood

import com.thudlm.vegetarianres.dependencies.entities.ExtraFood
import io.reactivex.Single

interface IExtraFoodRepository {

    suspend fun insert(extraFood: ExtraFood)

    suspend fun update(extraFood: ExtraFood)

    suspend fun delete(extraFood: ExtraFood)

    suspend fun deleteAll()

    fun getAll(): Single<List<ExtraFood>>

}