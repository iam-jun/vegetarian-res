package com.thudlm.vegetarianres.dependencies.repositories.product

import android.util.Log
import com.thudlm.vegetarianres.dependencies.dao.ProductDao
import com.thudlm.vegetarianres.dependencies.entities.Product
import com.thudlm.vegetarianres.dependencies.entities.ProductCategory
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers



class ProductRepository(private val productDao: ProductDao): IProductRepository {

    override suspend fun insert(product: Product) {
        Single.just(productDao.insert(product))
            .subscribeOn(Schedulers.io())
            .subscribe(object : DisposableSingleObserver<Any>() {
                override fun onSuccess(obj: Any) {
                    Log.d("RxJava", "Insert Success")
                    dispose()
                }

                override fun onError(e: Throwable) {
                    Log.d("RxJava", "Insert Error")
                    dispose()
                }
            })
    }

    override suspend fun update(product: Product) {
        Single.just(productDao.update(product))
            .subscribeOn(Schedulers.io())
            .subscribe(object : DisposableSingleObserver<Any>() {
                override fun onSuccess(obj: Any) {
                    Log.d("RxJava", "Update Success")
                    dispose()
                }

                override fun onError(e: Throwable) {
                    Log.d("RxJava", "Update Error")
                    dispose()
                }
            })
    }

    override suspend fun delete(product: Product) {
        Single.just(productDao.delete(product))
            .subscribeOn(Schedulers.io())
            .subscribe(object : DisposableSingleObserver<Any>() {
                override fun onSuccess(obj: Any) {
                    Log.d("RxJava", "Delete Success")
                    dispose()
                }

                override fun onError(e: Throwable) {
                    Log.d("RxJava", "Delete Error")
                    dispose()
                }
            })
    }

    override suspend fun deleteAll() {
        Single.just(productDao.deleteAll())
            .subscribeOn(Schedulers.io())
            .subscribe(object : DisposableSingleObserver<Any>() {
                override fun onSuccess(obj: Any) {
                    Log.d("RxJava", "Delete Success")
                    dispose()
                }

                override fun onError(e: Throwable) {
                    Log.d("RxJava", "Delete Error")
                    dispose()
                }
            })
        }

    override fun getProductsByCaterory(categoryId: Long): Single<List<ProductCategory>> = productDao.getProductsByCaterory(categoryId)

    override fun getProductById(id: Long): Single<Product> = productDao.getProductById(id)

    override fun searchProduct(keyword: String): Single<List<Product>> = productDao.searchProduct(keyword)

}