package com.darshan.androidtutorial.di.modules

import com.darshan.androidtutorial.news.api.DefaultNewsListService
import com.darshan.androidtutorial.news.api.NewsListService
import dagger.Binds
import dagger.Module

@Module
abstract class ServiceModule {

   @Binds
   abstract fun bindNewsListService(service: DefaultNewsListService): NewsListService

}