package com.tomergoldst.newsapp.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class NewsArticle(

    @SerializedName("source")
    var source: NewsArticleSource,

    @SerializedName("author")
    var author: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("url")
    var url: String,

    @SerializedName("urlToImage")
    var urlToImage: String,

    @SerializedName("publishedAt")
    var publishedAt: Date,

    @SerializedName("content")
    var content: String

)