package com.thudlm.vegetarianres.dependency.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "ProductDetail", foreignKeys = [ForeignKey(entity = Product::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("product_id"),
    onDelete = ForeignKey.CASCADE)]
)
class ProductDetail {

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0

    @ColumnInfo(name = "product_id")
    var productId: Long = 0

    @ColumnInfo(name = "weight")
    var weight: Int = 0

    @ColumnInfo(name = "description")
    var description: String? = null

    @ColumnInfo(name = "calories")
    var calories: Int = 0

    @ColumnInfo(name = "protein")
    var protein: Int = 0

    @ColumnInfo(name = "total_fat")
    var totalFat: Int = 0

    @ColumnInfo(name = "total_carbs")
    var totalCarbs: Int = 0
}