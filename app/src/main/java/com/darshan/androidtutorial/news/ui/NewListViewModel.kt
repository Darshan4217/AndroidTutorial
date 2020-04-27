package com.darshan.androidtutorial.news.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.darshan.androidtutorial.news.model.NewsListData
import com.darshan.androidtutorial.news.repository.NewsListRepository
import com.darshan.androidtutorial.utils.TransientAwareConsumerLiveData
import com.darshan.androidtutorial.utils.TransientAwareUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class NewsListUIModel : TransientAwareUiModel() {
    object Loading : NewsListUIModel()
    data class Error(var error: String = "") : NewsListUIModel()
    data class Success(val newsListModel: NewsListData)
}

class NewListViewModel @Inject constructor(val newsListRepository: NewsListRepository) :
    ViewModel() {

    private val _newsListUIModel= TransientAwareConsumerLiveData<NewsListUIModel>()
    var newsListUIModel: LiveData<NewsListUIModel> = _newsListUIModel


    @SuppressLint("CheckResult")
    fun getNewsData() {

        Log.d("","repos")
        Log.d("","repossss1")
       /* newsListRepository.getNewsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
            .map { NewsListUIModel.Success(it) as NewsListUIModel }
            .startWith(NewsListUIModel.Loading)
            .doOnError { "" }
            .onErrorReturn { NewsListUIModel.Error("") }
            .subscribe(_newsListUIModel)*/
       /* CoroutineScope(Dispatchers.IO + Job()).launch {
            _newsListUIModel.accept()

        }*/
    }
}
