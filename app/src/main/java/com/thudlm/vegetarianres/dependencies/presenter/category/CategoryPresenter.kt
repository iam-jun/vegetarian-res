package com.thudlm.vegetarianres.dependencies.presenter.category

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thudlm.vegetarianres.dependencies.entities.Category
import com.thudlm.vegetarianres.dependencies.interactor.category.ICategoryInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CategoryPresenter(private val iCategoryInteractor: ICategoryInteractor): ICategoryPresenter, ViewModel() {

    val categories : MutableLiveData<List<Category>> = MutableLiveData()

    init {
        loadCategory()
    }

    override suspend fun insert(category: Category) {
        iCategoryInteractor.insert(category)
    }

    override suspend fun update(category: Category) {
        iCategoryInteractor.update(category)
    }

    override suspend fun delete(category: Category) {
        iCategoryInteractor.delete(category)
    }

    override suspend fun deleteAll() {
        iCategoryInteractor.deleteAll()
    }

    @SuppressLint("CheckResult")
    override fun loadCategory() {
        iCategoryInteractor.getAllCategory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> categories.postValue(value) },
                { error -> Log.d("RxJava", "Error getting info from interactor into presenter $error") }
            )

    }

}