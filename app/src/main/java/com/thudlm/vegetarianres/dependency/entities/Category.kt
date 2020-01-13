package com.thudlm.vegetarianres.dependency.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(@PrimaryKey(autoGenerate = true) val id: Long?,
                    @ColumnInfo(name = "title") var title: String){
    override fun toString(): String {
        return "$id - $title"
    }
}