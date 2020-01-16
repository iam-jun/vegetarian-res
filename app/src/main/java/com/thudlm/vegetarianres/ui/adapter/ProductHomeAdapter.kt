package com.thudlm.vegetarianres.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thudlm.vegetarianres.R
import com.thudlm.vegetarianres.dependencies.entities.ProductCategory
import com.thudlm.vegetarianres.ui.callback.RecyclerViewItemClick
import com.thudlm.vegetarianres.ui.viewholder.ProductHomeViewHolder
import com.thudlm.vegetarianres.utils.AppContants.ORDER_TITLE_GRID
import com.thudlm.vegetarianres.utils.AppContants.ORDER_TITLE_LIST
import com.thudlm.vegetarianres.utils.AppContants.VIEW_TYPE_LIST
import com.thudlm.vegetarianres.utils.GlideUtils

class ProductHomeAdapter(
    private val context: Context,
    private val líst: ArrayList<ProductCategory>,
    private var viewMode: Int,
    private val recyclerViewItemClick: RecyclerViewItemClick
) :
    RecyclerView.Adapter<ProductHomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHomeViewHolder {
        return if (viewMode == VIEW_TYPE_LIST)
            ProductHomeViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_product_home_list, parent, false)
            )
        else
            ProductHomeViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_product_home_grid, parent, false)
            )
    }

    override fun getItemCount() = líst.size

    override fun onBindViewHolder(holder: ProductHomeViewHolder, position: Int) {
        líst[position].let {
            holder.tvName.text = it.product!!.name
            holder.tvCategory.text = it.category!![0].title
            if (viewMode == VIEW_TYPE_LIST)
                holder.tvTime.text = ORDER_TITLE_LIST
                    .replace(" MONEY", "${it.product!!.price}")
                    .replace("TIME", "${it.product!!.totalTime}")
            else
                holder.tvTime.text = ORDER_TITLE_GRID
                    .replace("PEOPLE", "${it.product!!.totalPeople}")
                    .replace("TIME", "${it.product!!.totalTime}")
            GlideUtils.load(context, it.product!!.thumbnail!!, holder.imgProduct)
            holder.itemView.setOnClickListener {
                recyclerViewItemClick.onItemClick(position)
            }
        }
    }

}