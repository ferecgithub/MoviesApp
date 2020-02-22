package com.ferechamitbeyli.moviesapp.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ferechamitbeyli.moviesapp.util.Constants

object ImageBindingAdapter {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        if(url.isNotEmpty()) {
            Glide.with(imageView.context)
                .load(Constants.IMAGE_BASE_URL + Constants.IMAGE_W342 + url)
                .into(imageView)
        }
    }
}