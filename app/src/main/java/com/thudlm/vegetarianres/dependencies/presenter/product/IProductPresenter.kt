package com.thudlm.vegetarianres.dependencies.presenter.product

import com.thudlm.vegetarianres.dependencies.entities.Product

interface IProductPresenter {

    suspend fun insert(product: Product)

    suspend fun update(product: Product)

    suspend fun delete(product: Product)

    suspend fun deleteAll()

    fun loadProductByCategory(categoryId: Long)

    fun getProductById(id: Long)

    fun searchProduct(keyword: String)
}