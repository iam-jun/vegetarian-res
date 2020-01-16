package com.thudlm.vegetarianres.dependencies.entities

import androidx.room.Embedded
import androidx.room.Relation


class ProductCategory {

    @Embedded
    var product: Product? = null

    @Relation(parentColumn = "category_id", entityColumn = "id", entity = Category::class)
    var category: List<Category>? = null

}