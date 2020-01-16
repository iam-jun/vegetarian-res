package com.thudlm.vegetarianres.dependencies.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "extra_food")
data class ExtraFood(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "price") var price: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}