package com.darshan.androidtutorial.di.modules

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.darshan.androidtutorial.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class SystemServiceModule {

    @Provides
    @ApplicationScope
    fun provideConnectivityManager(app: Application): ConnectivityManager {
        return app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}