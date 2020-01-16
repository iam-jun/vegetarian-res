package com.thudlm.vegetarianres.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.thudlm.vegetarianres.R
import com.thudlm.vegetarianres.dependencies.entities.Category

class CategoryAdapter(
    context: Context, var list: List<Category>
) : BaseAdapter() {
    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return list[position].id!!
    }

    override fun getCount(): Int {
        return list.size
    }

    var vi: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val retView: View = convertView ?: vi.inflate(R.layout.item_category, null)
        val textView: TextView = retView.findViewById(R.id.textView)

        textView.text = list[position].title
        return retView
    }
}