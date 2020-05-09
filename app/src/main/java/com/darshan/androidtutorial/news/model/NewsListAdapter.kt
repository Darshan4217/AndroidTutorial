package com.darshan.androidtutorial.news.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.darshan.androidtutorial.R
import kotlinx.android.synthetic.main.news_list_data.view.*
import java.util.*
import kotlin.collections.ArrayList

class NewsListAdapter(private var articles: List<Articles>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var articleList: ArrayList<Articles> = articles as ArrayList<Articles>
   // var newArticleList = articles as ArrayList<Articles>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.news_list_data, parent, false)
        return NewsListViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsListViewHolder).bind(articleList[position], position)
        // holder.setListeners()

        holder.itemView.image_delete.setOnClickListener {
            articleList.removeAt(position)
            /* notifyItemRemoved(position)
             notifyItemRangeChanged(position, articleList.size)*/
            notifyDataSetChanged()
        }

        holder.itemView.image_add.setOnClickListener {
            articleList.add(position, articleList[position])
            /*   notifyItemInserted(position)
               notifyItemRangeChanged(position, articleList.size)*/
            notifyDataSetChanged()
        }
    }

    class NewsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(articles: Articles, position: Int) {
            itemView.txtAuthor.text = articles.author
            itemView.txtTitle.text = articles.title

        }

        /*   fun setListeners() {
               itemView.image_delete.setOnClickListener(this)
           }*/
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = ArrayList<Articles>()
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filteredList.addAll(articles)
                } else {

                    for (row in articles) {
                        if (row.toString().toLowerCase(Locale.ROOT).contains(
                                charSearch.toLowerCase(
                                    Locale.ROOT
                                )
                            )
                        ) {
                            filteredList.add(row)
                        }
                    }
                }
                val filterResult = FilterResults()
                filterResult.values = filteredList
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
             //   articleList.clear()
                articleList = results?.values as ArrayList<Articles>
                notifyDataSetChanged()
            }
        }
    }


}