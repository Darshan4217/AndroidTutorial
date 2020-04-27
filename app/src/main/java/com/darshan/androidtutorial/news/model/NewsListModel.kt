package com.darshan.androidtutorial.news.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsListData(
    val status: String,
    val totalResults: String,
    val articles: List<Articles>
) : Parcelable

@Parcelize
data class Articles(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) : Parcelable

@Parcelize
data class Source(
    val id: String? = "",
    val name: String? = ""
) : Parcelable