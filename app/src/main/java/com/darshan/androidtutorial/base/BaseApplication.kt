package com.darshan.androidtutorial.base

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.darshan.androidtutorial.di.utils.applyAutoInjector
import com.darshan.androidtutorial.di.components.ApplicationComponent
import com.darshan.androidtutorial.di.components.DaggerApplicationComponent
import com.darshan.androidtutorial.di.modules.ApplicationModule
import com.darshan.androidtutorial.di.modules.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

open class BaseApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build()
            .inject(this)

        applyAutoInjector()
    }

}