package com.darshan.androidtutorial.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import io.reactivex.functions.Consumer


inline fun <reified T: ViewModel> FragmentActivity.bindViewModel(lazyViewModelProviderFactory: Lazy<ViewModelProvider.Factory>):Lazy<T>{
    return lazy { ViewModelProviders.of(this, lazyViewModelProviderFactory.value).get(T::class.java) }
}