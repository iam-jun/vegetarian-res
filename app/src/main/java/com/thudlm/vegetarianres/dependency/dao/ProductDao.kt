package com.thudlm.vegetarianres.dependency.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thudlm.vegetarianres.dependency.entities.Product
import io.reactivex.Observable

@Dao
interface  ProductDao {

    @Query("SELECT * from product inner join product_category as pc on product.id = pc.product_id left join category as c on pc.category_id = c.id where c.id = :categoryId")
    fun getProductsByCaterory(categoryId: Int): Observable<List<Product>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Query("DELETE FROM product")
    suspend fun deleteAll()

}