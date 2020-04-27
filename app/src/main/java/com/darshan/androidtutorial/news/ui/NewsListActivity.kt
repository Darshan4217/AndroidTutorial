package com.darshan.androidtutorial.news.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.darshan.androidtutorial.BaseActivity
import com.darshan.androidtutorial.R
import com.darshan.androidtutorial.utils.bindViewModel
import javax.inject.Inject

class NewsListActivity : BaseActivity() {
     @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by bindViewModel<NewListViewModel>(lazy { viewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
    }

    override fun onResume() {
        viewModel.getNewsData()
        super.onResume()
    }
}
