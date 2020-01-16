package com.thudlm.vegetarianres.di

import androidx.room.Room
import com.thudlm.vegetarianres.dependencies.injector.AppDatabase
import com.thudlm.vegetarianres.dependencies.interactor.category.CategoryInteractor
import com.thudlm.vegetarianres.dependencies.interactor.category.ICategoryInteractor
import com.thudlm.vegetarianres.dependencies.interactor.product.IProductInteractor
import com.thudlm.vegetarianres.dependencies.interactor.product.ProductInteractor
import com.thudlm.vegetarianres.dependencies.presenter.category.CategoryPresenter
import com.thudlm.vegetarianres.dependencies.presenter.product.ProductPresenter
import com.thudlm.vegetarianres.dependencies.repositories.category.CategoryRepository
import com.thudlm.vegetarianres.dependencies.repositories.category.ICategoryRepository
import com.thudlm.vegetarianres.dependencies.repositories.product.IProductRepository
import com.thudlm.vegetarianres.dependencies.repositories.product.ProductRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule : Module = module {

    single { Room.databaseBuilder(get(), AppDatabase::class.java, "app_database").build() }

    single { get<AppDatabase>().productDao() }

    single<IProductRepository> { ProductRepository(get()) }

    single<IProductInteractor> { ProductInteractor(get()) }

    single { get<AppDatabase>().categoryDao() }

    single<ICategoryRepository> { CategoryRepository(get()) }

    single<ICategoryInteractor> { CategoryInteractor(get()) }

    viewModel { ProductPresenter(get()) }

    viewModel { CategoryPresenter(get()) }
}