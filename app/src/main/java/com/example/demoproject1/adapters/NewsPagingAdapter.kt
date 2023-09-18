package com.example.demoproject1.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.demoproject1.models.Article
import com.example.demoproject1.viewHolders.NewsViewHolder

class NewsPagingAdapter: PagingDataAdapter<Article, NewsViewHolder>(COMPARATOR) {

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<Article>(){
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
        return NewsViewHolder.fromParent(parent)
    }

}