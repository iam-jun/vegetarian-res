package com.thudlm.vegetarianres.dependencies.dao

import androidx.room.*
import com.thudlm.vegetarianres.dependencies.entities.Category
import io.reactivex.Single

@Dao
interface CategoryDao {

    @Query("SELECT * from category")
    fun getAllCategory(): Single<List<Category>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("DELETE FROM category")
    suspend fun deleteAll()

}