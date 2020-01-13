package com.thudlm.vegetarianres.dependency.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "product")
class Product {

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "location_id")
    var locationId: Long? = null

    @ColumnInfo(name = "price")
    var price: Double? = null

    @ColumnInfo(name = "weight")
    var weight: Int? = null

    @ColumnInfo(name = "description")
    var description: String? = null

    @ColumnInfo(name = "calories")
    var calories: Int? = null

    @ColumnInfo(name = "protein")
    var protein: Int? = null

    @ColumnInfo(name = "total_fat")
    var totalFat: Int? = null

    @ColumnInfo(name = "total_carbs")
    var totalCarbs: Int? = null

    @ColumnInfo(name = "title")
    var address: String? = null

    @ColumnInfo(name = "latitude")
    var latitude: Double? = null

    @ColumnInfo(name = "longitude")
    var longitude: Double? = null
}