package com.thudlm.vegetarianres.dependency.injection

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thudlm.vegetarianres.dependency.dao.CategoryDao
import com.thudlm.vegetarianres.dependency.dao.ProductDao
import com.thudlm.vegetarianres.dependency.entities.Category
import com.thudlm.vegetarianres.dependency.entities.Product

@Database(entities = [Product::class, Category::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao() : ProductDao
    abstract fun categoryDao() : CategoryDao
}