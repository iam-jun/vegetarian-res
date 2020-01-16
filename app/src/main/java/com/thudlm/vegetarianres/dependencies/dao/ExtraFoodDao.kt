package com.thudlm.vegetarianres.dependencies.dao

import androidx.room.*
import com.thudlm.vegetarianres.dependencies.entities.ExtraFood
import io.reactivex.Single

@Dao
interface ExtraFoodDao {

    @Query("SELECT * from extra_food")
    fun getAll(): Single<List<ExtraFood>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(extraFood: ExtraFood)

    @Update
    suspend fun update(extraFood: ExtraFood)

    @Delete
    suspend fun delete(extraFood: ExtraFood)

    @Query("DELETE FROM extra_food")
    suspend fun deleteAll()

}