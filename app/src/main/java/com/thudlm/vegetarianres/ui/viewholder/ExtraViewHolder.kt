package com.thudlm.vegetarianres.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thudlm.vegetarianres.R

class ExtraViewHolder(v: View): RecyclerView.ViewHolder(v) {

    val tvName: TextView = v.findViewById(R.id.tvName)
    val tvPrice: TextView = v.findViewById(R.id.tvPrice)
    val icCheck: ImageView = v.findViewById(R.id.icCheck)

}