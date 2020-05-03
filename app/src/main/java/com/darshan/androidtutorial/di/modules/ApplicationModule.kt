package com.darshan.androidtutorial.di.modules

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.darshan.androidtutorial.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @ApplicationScope
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }

    @Provides
    @ApplicationScope
    fun provideApplication(): Application {
        return application
    }

    @Provides
    @ApplicationScope
    fun provideApplicationResource(application: Application): Resources {
        return application.resources
    }

}