package com.thudlm.vegetarianres.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.thudlm.vegetarianres.R


object GlideUtils {

    private val glideOptions = RequestOptions()
        .error(R.drawable.ic_no_image)
        .centerCrop()

    private fun createLoading(context: Context): CircularProgressDrawable{
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        return circularProgressDrawable
    }

    fun load(context: Context, uri: String, target: ImageView){
        Glide.with(context)
            .load(uri)
            .placeholder(createLoading(context))
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .apply(glideOptions).into(target)
    }

    fun loadNoCache(context: Context, uri: String, target: ImageView){
        Glide.with(context)
            .load(uri)
            .placeholder(createLoading(context))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .apply(glideOptions).into(target)
    }

}