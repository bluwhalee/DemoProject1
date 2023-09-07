package com.example.demoproject1.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.demoproject1.models.Article
import com.example.demoproject1.models.News
import com.example.demoproject1.retrofit.NewsApi

class NewsPagingSource(val newsApi: NewsApi) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val position = params.key ?: 1
            val response = newsApi.getNews(position)
            LoadResult.Page(
                data = response.articles,
                prevKey = if(position==1) null else position-1,
                nextKey = if(position==response.totalResults) null else position+1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}