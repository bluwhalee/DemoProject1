package com.example.demoproject1.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.demoproject1.models.Article
import com.example.demoproject1.models.News
import com.example.demoproject1.retrofit.NewsApi
import com.example.demoproject1.utils.Constants

class NewsPagingSource(private val newsApi: NewsApi) : PagingSource<Int, Article>() {

    //Implemented Members
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(Constants.numbers.one)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(Constants.numbers.one)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val position = params.key ?: Constants.numbers.one
            val response = newsApi.getNews(position)
            LoadResult.Page(
                data = response.articles,
                prevKey = if(position==Constants.numbers.one) null else position-Constants.numbers.one,
                nextKey = if(position==response.totalResults) null else position+Constants.numbers.one
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}