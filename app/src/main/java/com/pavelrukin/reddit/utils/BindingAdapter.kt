package com.pavelrukin.reddit.utils

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter( "imageUrl", "errorImage" )
fun loadImage(view: AppCompatImageView, url: String, errorDrawable: Drawable) {
    if(url.isNotEmpty()) {
        Picasso.get()
            .load(url)
            .error(errorDrawable)
            .into(view)
    }

}