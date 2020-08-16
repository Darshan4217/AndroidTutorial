package com.darshan.androidtutorial.ui.news.repository

import com.darshan.androidtutorial.ui.news.api.NewsListService
import com.darshan.androidtutorial.ui.news.model.NewsListData
import io.reactivex.Single
import javax.inject.Inject

class DefaultNewsListRepository @Inject constructor(private val newsListService: NewsListService) :
    NewsListRepository {

    override fun getNewsList(): Single<NewsListData> {
        return newsListService.getNewsList()
    }
}