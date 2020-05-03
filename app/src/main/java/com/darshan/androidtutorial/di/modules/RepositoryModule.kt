package com.darshan.androidtutorial.di.modules

import com.darshan.androidtutorial.news.repository.DefaultNewsListRepository
import com.darshan.androidtutorial.news.repository.NewsListRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsListRepository(repository: DefaultNewsListRepository): NewsListRepository

}