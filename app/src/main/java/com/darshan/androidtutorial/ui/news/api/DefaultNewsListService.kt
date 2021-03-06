package com.darshan.androidtutorial.ui.news.api

import com.darshan.androidtutorial.di.scopes.ApplicationScope
import com.darshan.androidtutorial.ui.news.model.NewsListData
import io.reactivex.Single
import javax.inject.Inject

@ApplicationScope
class DefaultNewsListService @Inject constructor(val service: NewsListServiceBase) :
    NewsListService {

    override fun getNewsList(): Single<NewsListData> {
        return service.getNewsList()
    }
}