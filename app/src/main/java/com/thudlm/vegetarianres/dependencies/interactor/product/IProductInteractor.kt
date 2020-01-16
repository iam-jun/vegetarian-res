package com.thudlm.vegetarianres.dependencies.interactor.product

import com.thudlm.vegetarianres.dependencies.entities.Product
import com.thudlm.vegetarianres.dependencies.entities.ProductCategory
import io.reactivex.Single

interface IProductInteractor {

    suspend fun insert(product: Product)

    suspend fun update(product: Product)

    suspend fun delete(product: Product)

    suspend fun deleteAll()

    fun getProductsByCaterory(categoryId: Long): Single<List<ProductCategory>>

    fun getProductById(id: Long) :Single<Product>

    fun searchProduct(keyword: String): Single<List<Product>>

}