package com.tomergoldst.newsapp.model

import com.google.gson.annotations.SerializedName

data class NewsArticleSource(

    @SerializedName("id")
    var id: String?,

    @SerializedName("name")
    var name: String

)