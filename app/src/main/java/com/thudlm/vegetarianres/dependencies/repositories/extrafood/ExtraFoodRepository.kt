package com.thudlm.vegetarianres.dependencies.repositories.extrafood

import android.util.Log
import com.thudlm.vegetarianres.dependencies.dao.ExtraFoodDao
import com.thudlm.vegetarianres.dependencies.entities.ExtraFood
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ExtraFoodRepository(private val extraFoodDao: ExtraFoodDao): IExtraFoodRepository {

    override suspend fun insert(extraFood: ExtraFood) {
        Single.just(extraFoodDao.insert(extraFood))
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

    override suspend fun update(extraFood: ExtraFood) {
        Single.just(extraFoodDao.update(extraFood))
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

    override suspend fun delete(extraFood: ExtraFood) {
        Single.just(extraFoodDao.delete(extraFood))
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
        Single.just(extraFoodDao.deleteAll())
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

    override fun getAll(): Single<List<ExtraFood>> = extraFoodDao.getAll()

}