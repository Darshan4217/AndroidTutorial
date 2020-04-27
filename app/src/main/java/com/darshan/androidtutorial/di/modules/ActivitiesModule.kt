package com.darshan.androidtutorial.di.modules

import com.darshan.androidtutorial.di.scopes.ActivityScope
import com.darshan.androidtutorial.news.ui.NewsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeNewsListActivity(): NewsListActivity
}