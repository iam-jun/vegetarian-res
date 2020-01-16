package com.thudlm.vegetarianres.dependencies.injector

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thudlm.vegetarianres.dependencies.dao.CategoryDao
import com.thudlm.vegetarianres.dependencies.dao.ExtraFoodDao
import com.thudlm.vegetarianres.dependencies.dao.ProductDao
import com.thudlm.vegetarianres.dependencies.entities.Category
import com.thudlm.vegetarianres.dependencies.entities.ExtraFood
import com.thudlm.vegetarianres.dependencies.entities.Product

@Database(entities = [Product::class, Category::class, ExtraFood::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao() : ProductDao
    abstract fun categoryDao() : CategoryDao
    abstract fun extraFoodDao() : ExtraFoodDao
}