package com.thudlm.vegetarianres.dependencies.dao

import androidx.room.*
import com.thudlm.vegetarianres.dependencies.entities.Product
import com.thudlm.vegetarianres.dependencies.entities.ProductCategory
import io.reactivex.Single

@Dao
interface  ProductDao {

    @Query("SELECT * from product left join category as c on product.category_id = c.id where c.id = :categoryId")
    fun getProductsByCaterory(categoryId: Long): Single<List<ProductCategory>>

    @Query("SELECT * from product where productId = :id")
    fun getProductById(id: Long): Single<Product>

    @Query("SELECT * from product where name like :keyword limit 8")
    fun searchProduct(keyword: String): Single<List<Product>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("DELETE FROM product")
    suspend fun deleteAll()

}