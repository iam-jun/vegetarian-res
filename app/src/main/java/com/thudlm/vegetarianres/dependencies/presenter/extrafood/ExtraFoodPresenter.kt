package com.thudlm.vegetarianres.dependencies.presenter.extrafood

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thudlm.vegetarianres.dependencies.entities.ExtraFood
import com.thudlm.vegetarianres.dependencies.interactor.extrafood.IExtraFoodInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ExtraFoodPresenter(private val iExtraFoodInteractor: IExtraFoodInteractor): IExtraFoodPresenter, ViewModel() {

    val extraFoods : MutableLiveData<List<ExtraFood>> = MutableLiveData()

    init {
        loadExtraFoods()
    }

    override suspend fun insert(extraFood: ExtraFood) {
        iExtraFoodInteractor.insert(extraFood)
    }

    override suspend fun update(extraFood: ExtraFood) {
        iExtraFoodInteractor.update(extraFood)
    }

    override suspend fun delete(extraFood: ExtraFood) {
        iExtraFoodInteractor.delete(extraFood)
    }

    override suspend fun deleteAll() {
        iExtraFoodInteractor.deleteAll()
    }

    @SuppressLint("CheckResult")
    override fun loadExtraFoods() {
        iExtraFoodInteractor.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> extraFoods.postValue(value) },
                { error -> Log.d("RxJava", "Error getting info from interactor into presenter $error") }
            )

    }

}