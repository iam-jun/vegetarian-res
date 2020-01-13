package com.thudlm.vegetarianres.dependency.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thudlm.vegetarianres.dependency.entities.Category
import io.reactivex.Observable

@Dao
interface CategoryDao {

    @Query("SELECT * from category")
    fun getAllCategory(): Observable<List<Category>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

    @Query("DELETE FROM category")
    suspend fun deleteAll()

}