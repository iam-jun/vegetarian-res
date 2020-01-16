package com.thudlm.vegetarianres.dependencies.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(@ColumnInfo(name = "title") var title: String){

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}