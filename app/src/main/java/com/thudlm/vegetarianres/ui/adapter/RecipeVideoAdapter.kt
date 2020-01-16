package com.thudlm.vegetarianres.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thudlm.vegetarianres.R
import com.thudlm.vegetarianres.dependencies.entities.Product
import com.thudlm.vegetarianres.ui.callback.RecipesItemClick
import com.thudlm.vegetarianres.ui.viewholder.RecipeViewHolder
import com.thudlm.vegetarianres.utils.AppContants.ORDER_TITLE_GRID
import com.thudlm.vegetarianres.utils.GlideUtils

class RecipeVideoAdapter(
    private val context: Context,
    private val list: ArrayList<Product>,
    private val recipesItemClick: RecipesItemClick
) : RecyclerView.Adapter<RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recipes_video_search_result, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        list[position].let {
            holder.tvName.text = it.name
            holder.tvTime.text = ORDER_TITLE_GRID
                .replace("PEOPLE", "${it.totalPeople}")
                .replace("TIME", "${it.totalTime}")

            GlideUtils.load(context, it.thumbnail!!, holder.imgProduct)
            holder.itemView.setOnClickListener {
                recipesItemClick.onItemClick(list[position].productId)
            }
        }
    }

}