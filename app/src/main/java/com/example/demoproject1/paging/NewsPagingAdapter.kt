package com.example.demoproject1.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoproject1.MainApplication
import com.example.demoproject1.R
import com.example.demoproject1.models.Article

class NewsPagingAdapter: PagingDataAdapter<Article, NewsPagingAdapter.NewsViewHolder>(COMPARATOR) {

    class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
        var newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
        var newsDescription = itemView.findViewById<TextView>(R.id.newsDescription)
    }

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

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)

        holder.newsTitle.text = article?.title
        holder.newsDescription.text = article?.description
        //Glide.with(MainApplication.applicationContext()).load(article?.urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener{
            //Toast.makeText(MainApplication.instance?.applicationContext,article?.title,Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }
}