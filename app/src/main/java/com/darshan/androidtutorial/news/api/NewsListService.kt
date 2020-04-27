package com.darshan.androidtutorial.news.api

import com.darshan.androidtutorial.di.RetrofitServiceWrapper
import com.darshan.androidtutorial.news.model.NewsListData
import io.reactivex.Single

interface NewsListService: RetrofitServiceWrapper{
    fun getNewsList(): Single<NewsListData>
}