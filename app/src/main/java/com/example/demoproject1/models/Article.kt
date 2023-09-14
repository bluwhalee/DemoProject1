package com.example.demoproject1.models

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String
) {
    companion object {
        @JvmStatic
        @BindingAdapter("urlToImage")
        fun loadImage(view: ImageView, urlToImage: String?) {
            if (!urlToImage.isNullOrEmpty()) {
                Glide.with(view.context).load(urlToImage).into(view)
            }
        }
    }
}