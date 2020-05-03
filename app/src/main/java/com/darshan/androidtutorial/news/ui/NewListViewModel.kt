package com.darshan.androidtutorial.news.ui

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.darshan.androidtutorial.news.model.NewsListData
import com.darshan.androidtutorial.news.repository.NewsListRepository
import com.darshan.androidtutorial.utils.DisposableViewModel
import com.darshan.androidtutorial.utils.State
import com.darshan.androidtutorial.utils.TransientAwareUiModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx2.await
import javax.inject.Inject

sealed class NewsListUIModel : TransientAwareUiModel() {
    object Loading : NewsListUIModel()
    data class Error(var error: String = "") : NewsListUIModel()
    data class Success(val newsListModel: NewsListData) : NewsListUIModel()
}

class NewListViewModel @Inject constructor(val newsListRepository: NewsListRepository) :
    DisposableViewModel() {

    //private val _newsListUIModel = TransientAwareConsumerLiveData<NewsListUIModel>()
    // var newsListUIModel: LiveData<NewsListUIModel> = _newsListUIModel

    private val _newsListUIModel = MutableLiveData<State<NewsListData>>()
    var newsListUIModel: LiveData<State<NewsListData>> = _newsListUIModel


    @SuppressLint("CheckResult")
    fun getNewsData() {
        /*  newsListRepository.getNewsList()
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .toObservable()
              .map { NewsListUIModel.Success(it) as NewsListUIModel }
              .startWith(NewsListUIModel.Loading)
              .doOnError { "" }
              .onErrorReturn { NewsListUIModel.Error("") }
              .subscribe(_newsListUIModel)*/
        bgScope.launch {
            // _newsListUIModel.access(NewsListUIModel.Loading)
            _newsListUIModel.postValue(State.loading())
            try {
                val response = newsListRepository.getNewsList().await()
                //  _newsListUIModel.access(NewsListUIModel.Success(response))
                _newsListUIModel.postValue(State.Success(response))

            } catch (e: Exception) {
                // _newsListUIModel.access(NewsListUIModel.Error(e.message.toString()))
                _newsListUIModel.postValue(State.Error(e.message.toString()))
            }

        }
    }
}
