package com.thudlm.vegetarianres.dependencies.repositories.category

import android.util.Log
import com.thudlm.vegetarianres.dependencies.dao.CategoryDao
import com.thudlm.vegetarianres.dependencies.entities.Category
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers



class CategoryRepository(private val categoryDao: CategoryDao): ICategoryRepository {

    override suspend fun insert(category: Category) {
        Single.just(categoryDao.insert(category))
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

    override suspend fun update(category: Category) {
        Single.just(categoryDao.update(category))
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

    override suspend fun delete(category: Category) {
        Single.just(categoryDao.delete(category))
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
        Single.just(categoryDao.deleteAll())
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

    override fun getAllCategory(): Single<List<Category>> = categoryDao.getAllCategory()

}