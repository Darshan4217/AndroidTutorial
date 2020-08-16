package com.darshan.androidtutorial.ui.news.api

import com.darshan.androidtutorial.di.utils.RetrofitServiceWrapper
import com.darshan.androidtutorial.ui.news.model.NewsListData
import io.reactivex.Single

interface NewsListService : RetrofitServiceWrapper {
    fun getNewsList(): Single<NewsListData>
}