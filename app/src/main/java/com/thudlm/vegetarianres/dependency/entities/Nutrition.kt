package com.thudlm.vegetarianres.dependency.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nutrition")
class Nutrition {

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

    @ColumnInfo(name = "calories")
    var calories: Int = 0

    @ColumnInfo(name = "protein")
    var protein: Int = 0

    @ColumnInfo(name = "total_fat")
    var totalFat: Int = 0

    @ColumnInfo(name = "total_carbs")
    var totalCarbs: Int = 0
}