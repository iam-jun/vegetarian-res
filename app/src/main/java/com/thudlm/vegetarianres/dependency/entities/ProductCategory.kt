package com.thudlm.vegetarianres.dependency.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "product_category", foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = arrayOf("product_id"),
            childColumns = arrayOf("id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Category::class,
            parentColumns = arrayOf("category_id"),
            childColumns = arrayOf("id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class ProductCategory {

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0

    @ColumnInfo(name = "product_id")
    var productId: Long = 0

    @ColumnInfo(name = "category_id")
    var categoryId: Long = 0
}