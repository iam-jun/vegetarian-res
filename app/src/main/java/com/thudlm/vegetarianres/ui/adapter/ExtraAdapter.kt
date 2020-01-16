package com.thudlm.vegetarianres.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thudlm.vegetarianres.R
import com.thudlm.vegetarianres.dependencies.entities.ExtraFood
import com.thudlm.vegetarianres.ui.callback.RecyclerViewItemClick
import com.thudlm.vegetarianres.ui.viewholder.ExtraViewHolder

class ExtraAdapter(
    private val context: Context,
    private val list: ArrayList<ExtraFood>,
    var selectedPosition: Int = -1,
    private val recyclerViewItemClick: RecyclerViewItemClick
) : RecyclerView.Adapter<ExtraViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtraViewHolder {
        return ExtraViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_extra, parent, false)
        )
    }

    override fun getItemCount() = list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ExtraViewHolder, position: Int) {
        list[position].let {
            holder.tvName.text = "Add ${it.title}"
            holder.tvPrice.text = "+$"+it.price

            holder.itemView.setOnClickListener {
                recyclerViewItemClick.onItemClick(position)
            }
        }
        if(position == selectedPosition) holder.icCheck.visibility = View.VISIBLE
        else holder.icCheck.visibility = View.INVISIBLE
    }

}