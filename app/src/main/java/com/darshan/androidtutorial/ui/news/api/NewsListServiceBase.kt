package com.darshan.androidtutorial.ui.news.api

import com.darshan.androidtutorial.ui.news.model.NewsListData
import io.reactivex.Single
import retrofit2.http.GET

interface NewsListServiceBase {

    @GET("/v2/everything?q=bitcoin&from=2020-07-16&sortBy=publishedAt&apiKey=d3a8808b8a07463bbdeda5a417f50bd5")
    fun getNewsList(): Single<NewsListData>
}