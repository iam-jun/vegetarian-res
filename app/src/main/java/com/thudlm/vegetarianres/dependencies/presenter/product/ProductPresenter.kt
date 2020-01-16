package com.thudlm.vegetarianres.dependencies.presenter.product

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thudlm.vegetarianres.dependencies.entities.Product
import com.thudlm.vegetarianres.dependencies.entities.ProductCategory
import com.thudlm.vegetarianres.dependencies.interactor.product.IProductInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductPresenter(private val iProductInteractor: IProductInteractor): IProductPresenter, ViewModel() {

    val products : MutableLiveData<List<ProductCategory>> = MutableLiveData()
    val productsSearchResult : MutableLiveData<List<Product>> = MutableLiveData()
    val product : MutableLiveData<Product> = MutableLiveData()

    override suspend fun insert(product: Product) {
        iProductInteractor.insert(product)
    }

    override suspend fun update(product: Product) {
        iProductInteractor.update(product)
    }

    override suspend fun delete(product: Product) {
        iProductInteractor.delete(product)
    }

    override suspend fun deleteAll() {
        iProductInteractor.deleteAll()
    }

    @SuppressLint("CheckResult")
    override fun loadProductByCategory(categoryId: Long) {
        iProductInteractor.getProductsByCaterory(categoryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> products.postValue(value) },
                { error -> Log.d("RxJava", "Error getting info from interactor into presenter $error") }
            )

    }

    @SuppressLint("CheckResult")
    override fun getProductById(id: Long) {
        iProductInteractor.getProductById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> product.postValue(value) },
                { error -> Log.d("RxJava", "Error getting info from interactor into presenter $error") }
            )
    }

    @SuppressLint("CheckResult")
    override fun searchProduct(keyword: String) {
        iProductInteractor.searchProduct(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> productsSearchResult
                    .postValue(value) },
                { error -> Log.d("RxJava", "Error getting info from interactor into presenter $error") }
            )
    }

}