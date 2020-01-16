package com.thudlm.vegetarianres.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thudlm.vegetarianres.R

class ProductHomeViewHolder(v: View): RecyclerView.ViewHolder(v) {

    val imgProduct: ImageView = v.findViewById(R.id.imgProduct)
    val tvName: TextView = v.findViewById(R.id.tvName)
    val tvCategory: TextView = v.findViewById(R.id.tvCategory)
    val tvTime: TextView = v.findViewById(R.id.tvTime)

}