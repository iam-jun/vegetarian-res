package com.thudlm.vegetarianres.dependencies.interactor.product

import com.thudlm.vegetarianres.dependencies.entities.Product
import com.thudlm.vegetarianres.dependencies.entities.ProductCategory
import com.thudlm.vegetarianres.dependencies.repositories.product.IProductRepository
import io.reactivex.Single

class ProductInteractor(private val iProductRepository: IProductRepository): IProductInteractor {

    override suspend fun insert(product: Product) {
        iProductRepository.insert(product)
    }

    override suspend fun update(product: Product) {
        iProductRepository.update(product)
    }

    override suspend fun delete(product: Product) {
        iProductRepository.delete(product)
    }

    override suspend fun deleteAll() {
        iProductRepository.deleteAll()

    }

    override fun getProductsByCaterory(categoryId: Long): Single<List<ProductCategory>> {
        return iProductRepository.getProductsByCaterory(categoryId)
    }

    override fun getProductById(id: Long): Single<Product> {
        return iProductRepository.getProductById(id)
    }

    override fun searchProduct(keyword: String): Single<List<Product>> {
        return iProductRepository.searchProduct(keyword)
    }

}