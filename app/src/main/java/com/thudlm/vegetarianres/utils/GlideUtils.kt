package com.thudlm.vegetarianres.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.thudlm.vegetarianres.R


object GlideUtils {

    fun load(context: Context, uri: String, target: ImageView){
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        val glideOptions = RequestOptions()
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_no_image)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide.with(context).load(uri).apply(glideOptions).into(target)
    }

}