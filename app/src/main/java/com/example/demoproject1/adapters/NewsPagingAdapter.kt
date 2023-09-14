package com.example.demoproject1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoproject1.MainApplication
import com.example.demoproject1.R
import com.example.demoproject1.databinding.ItemContactBinding
import com.example.demoproject1.databinding.NewsItemBinding
import com.example.demoproject1.models.Article
import com.example.demoproject1.viewHolders.ContactViewHolder
import com.example.demoproject1.viewHolders.NewsViewHolder

class NewsPagingAdapter: PagingDataAdapter<Article, NewsViewHolder>(COMPARATOR) {

    // Companian
    companion object{
        private val COMPARATOR = object :DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }
        }
    }

    //Override Func
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

}