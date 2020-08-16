package com.darshan.androidtutorial.ui.news.ui

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.darshan.androidtutorial.base.BaseActivity
import com.darshan.androidtutorial.R
import com.darshan.androidtutorial.ui.news.model.NewsListAdapter
import com.darshan.androidtutorial.ui.news.model.NewsListData
import com.darshan.androidtutorial.utils.State
import com.darshan.androidtutorial.utils.bindViewModel
import com.darshan.androidtutorial.utils.makeGone
import com.darshan.androidtutorial.utils.makeVisible
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

        // Recycler view layout manager
        val layoutManager = LinearLayoutManager(this)
        //val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        // val layoutManager = GridLayoutManager(this, 2)
        // layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        //  layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        // Recycler view item animation
        recyclerView.itemAnimator = DefaultItemAnimator()

        viewModel.newsListUIModel.observe(this, Observer { onUiModelChanged(it!!) })
        //viewModel.newsListUIModel.observe(this, Observer { onUiModelChanged(it!!) })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.articles_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)

        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                newsListAdapter.filter.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })

        return true
    }


    private fun onUiModelChanged(state: State<NewsListData>) {
        return when (state) {
            is State.Loading -> {
                progressBar.makeVisible()
            }
            is State.Success -> {
                progressBar.makeGone()
                newsListAdapter =
                    NewsListAdapter(state.data.articles)
                recyclerView.adapter = newsListAdapter
            }
            is State.Error -> {
                progressBar.makeGone()
                Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
            }
        }
    }


   /* private fun onUiModelChanged(uiModel: NewsListUIModel) {
        return when (uiModel) {
         if (uiModel.isRedelivered)
            return
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

    }*/

    override fun onResume() {
        viewModel.getNewsData()
        super.onResume()
    }
}
