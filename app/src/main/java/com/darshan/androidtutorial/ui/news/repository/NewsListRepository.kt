package com.darshan.androidtutorial.ui.news.repository

import com.darshan.androidtutorial.ui.news.model.NewsListData
import com.darshan.androidtutorial.repository.Repository
import io.reactivex.Single

interface NewsListRepository : Repository {

    fun getNewsList(): Single<NewsListData>
}