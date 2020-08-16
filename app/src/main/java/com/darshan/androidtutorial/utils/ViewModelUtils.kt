package com.darshan.androidtutorial.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import okhttp3.Dispatcher

/**
 * Get a ViewModel for the [Activity]
 */
inline fun <reified T : ViewModel> FragmentActivity.bindViewModel(lazyViewModelProviderFactory: Lazy<ViewModelProvider.Factory>): Lazy<T> {
    return lazy {
        ViewModelProviders.of(this, lazyViewModelProviderFactory.value).get(T::class.java)
    }
}

/**
 * Get a ViewModel for the  [Fragment]
 */
inline fun <reified T : ViewModel> Fragment.bindViewModel(lazyViewModelProviderFactory: Lazy<ViewModelProvider.Factory>): Lazy<T> {
    return lazy {
        ViewModelProviders.of(this, lazyViewModelProviderFactory.value).get(T::class.java)
    }
}

abstract class DisposableViewModel: ViewModel(){
    private val job = Job()
    protected  val disposable = CompositeDisposable()
    protected val scope = CoroutineScope(Dispatchers.Main + job)
    protected  val bgScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        disposable.clear()
        job.cancel()
    }
}

