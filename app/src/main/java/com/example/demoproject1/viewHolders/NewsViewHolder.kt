package com.example.demoproject1.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoproject1.R
import com.example.demoproject1.databinding.ItemContactBinding
import com.example.demoproject1.databinding.NewsItemBinding
import com.example.demoproject1.models.Article
import com.example.demoproject1.models.News

class NewsViewHolder (private val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(article: Article?){
        binding.apply {
            this@apply.article = article
            newsTitle.text = article?.title
            newsDescription.text = article?.description
        }

    }

    companion object{
        fun fromParent(parent: ViewGroup,):NewsViewHolder{
            return NewsViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }
}