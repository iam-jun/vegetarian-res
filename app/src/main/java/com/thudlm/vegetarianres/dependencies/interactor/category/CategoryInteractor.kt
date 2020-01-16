package com.thudlm.vegetarianres.dependencies.interactor.category

import com.thudlm.vegetarianres.dependencies.entities.Category
import com.thudlm.vegetarianres.dependencies.repositories.category.ICategoryRepository
import io.reactivex.Single

class CategoryInteractor(private val iCategoryRepository: ICategoryRepository): ICategoryInteractor {

    override suspend fun insert(category: Category) {
        iCategoryRepository.insert(category)
    }

    override suspend fun update(category: Category) {
        iCategoryRepository.update(category)
    }

    override suspend fun delete(category: Category) {
        iCategoryRepository.delete(category)
    }

    override suspend fun deleteAll() {
        iCategoryRepository.deleteAll()

    }

    override fun getAllCategory(): Single<List<Category>> {
        return iCategoryRepository.getAllCategory()
    }

}