package com.example.demoproject1.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.example.demoproject1.models.Article

class GenericDiffUtil(
    private val oldList:List<Article>,
    private val newList:List<Article>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].url == newList[newItemPosition].url
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].equals(newList[newItemPosition])
    }
}
class ArticleDiffUtil : DiffUtil.ItemCallback<Article>(){

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }
}