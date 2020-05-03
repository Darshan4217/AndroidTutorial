package com.darshan.androidtutorial.news.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.darshan.androidtutorial.R
import kotlinx.android.synthetic.main.news_list_data.view.*

class NewsListAdapter(articles: List<Articles>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var articleList: List<Articles> = mutableListOf()

    init {
        articleList = articles
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.news_list_data, parent, false)
        return NewsListViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsListViewHolder).bind(articleList[position])
    }

    class NewsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(articles: Articles) {
            itemView.txtAuthor.text = articles.author
            itemView.txtTitle.text = articles.title

        }
    }
}