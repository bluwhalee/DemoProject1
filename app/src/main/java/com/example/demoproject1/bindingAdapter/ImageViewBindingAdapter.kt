package com.example.demoproject1.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("urlToImage")
fun loadImage(view: ImageView, urlToImage: String?) {
    if (!urlToImage.isNullOrEmpty()) {
        // util method
        Glide.with(view.context).load(urlToImage).into(view)
    }
}