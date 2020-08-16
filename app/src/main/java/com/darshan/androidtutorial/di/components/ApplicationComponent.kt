package com.darshan.androidtutorial.di.components

import com.darshan.androidtutorial.base.BaseApplication
import com.darshan.androidtutorial.di.modules.*
import com.darshan.androidtutorial.di.scopes.ApplicationScope
import dagger.Component
import dagger.android.AndroidInjectionModule

@ApplicationScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ActivitiesModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        ServiceModule::class,
        SystemServiceModule::class
    ]
)

interface ApplicationComponent {
    fun inject(application: BaseApplication)
}
