package com.darshan.androidtutorial.news.api

import com.darshan.androidtutorial.news.model.NewsListData
import io.reactivex.Single
import retrofit2.http.GET

interface NewsListServiceBase {

    @GET("/everything?q=bitcoin&from=2020-04-25&sortBy=publishedAt&apiKey=d3a8808b8a07463bbdeda5a417f50bd5")
    fun getNewsList(): Single<NewsListData>
}