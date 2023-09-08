package com.example.demoproject1.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.demoproject1.paging.NewsPagingSource
import com.example.demoproject1.retrofit.NewsApi
import javax.inject.Inject
import javax.inject.Singleton


class NewsRepository (val  newsApi: NewsApi) {

    fun getNews() = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100),
        pagingSourceFactory = {NewsPagingSource(newsApi)}
    ).liveData
}