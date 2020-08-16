package com.darshan.androidtutorial.di.modules

import com.darshan.androidtutorial.ui.news.api.DefaultNewsListService
import com.darshan.androidtutorial.ui.news.api.NewsListService
import dagger.Binds
import dagger.Module

@Module
abstract class ServiceModule {

    @Binds
    abstract fun bindNewsListService(service: DefaultNewsListService): NewsListService

}