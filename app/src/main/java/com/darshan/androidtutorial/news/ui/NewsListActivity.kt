package com.darshan.androidtutorial.news.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.darshan.androidtutorial.BaseActivity
import com.darshan.androidtutorial.R
import com.darshan.androidtutorial.news.model.NewsListAdapter
import com.darshan.androidtutorial.news.model.NewsListData
import com.darshan.androidtutorial.utils.State
import com.darshan.androidtutorial.utils.bindViewModel
import kotlinx.android.synthetic.main.activity_news_list.*
import javax.inject.Inject

class NewsListActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by bindViewModel<NewListViewModel>(lazy { viewModelFactory })

    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        recyclerView.layoutManager = LinearLayoutManager(this)

        //viewModel.newsListUIModel.observe(this, Observer { onUiModelChanged(it!!) })
        viewModel.newsListUIModel.observe(this, Observer { onUiModelChanged(it!!) })
    }



    private fun onUiModelChanged(state: State<NewsListData>) {
        return when (state) {
            is State.Loading -> {

            }
            is State.Success -> {

                newsListAdapter = NewsListAdapter(state.data.articles)
                recyclerView.adapter = newsListAdapter
            }
            is State.Error -> {
                Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
            }
            else -> {
            }
        }

    }


    /* private fun onUiModelChanged(uiModel: NewsListUIModel) {
        return when (uiModel) {
            is NewsListUIModel.Loading -> {

            }
            is NewsListUIModel.Success -> {

                newsListAdapter = NewsListAdapter(uiModel.newsListModel.articles)
                recyclerView.adapter = newsListAdapter

                // val resu= uiModel.newsListModel.status

            }
            is NewsListUIModel.Error -> {
                Toast.makeText(this, uiModel.error, Toast.LENGTH_LONG).show()
            }
            else -> {
            }
        }

    }
*/
    override fun onResume() {
        viewModel.getNewsData()
        super.onResume()
    }
}
