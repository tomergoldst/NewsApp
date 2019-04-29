package com.tomergoldst.newsapp.model

import com.google.gson.annotations.SerializedName

data class NewsArticlesResponse(

    @SerializedName("status")
    var status: String,

    @SerializedName("totalResults")
    var totalResults: Int,

    @SerializedName("articles")
    var articles: List<NewsArticle> = ArrayList()


)