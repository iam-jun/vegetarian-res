package com.thudlm.vegetarianres.dependencies.interactor.extrafood

import com.thudlm.vegetarianres.dependencies.entities.ExtraFood
import com.thudlm.vegetarianres.dependencies.repositories.extrafood.IExtraFoodRepository
import io.reactivex.Single

class ExtraFoodInteractor(private val iExtraFoodRepository: IExtraFoodRepository): IExtraFoodInteractor {

    override suspend fun insert(extraFood: ExtraFood) {
        iExtraFoodRepository.insert(extraFood)
    }

    override suspend fun update(extraFood: ExtraFood) {
        iExtraFoodRepository.update(extraFood)
    }

    override suspend fun delete(extraFood: ExtraFood) {
        iExtraFoodRepository.delete(extraFood)
    }

    override suspend fun deleteAll() {
        iExtraFoodRepository.deleteAll()

    }

    override fun getAll(): Single<List<ExtraFood>> {
        return iExtraFoodRepository.getAll()
    }

}