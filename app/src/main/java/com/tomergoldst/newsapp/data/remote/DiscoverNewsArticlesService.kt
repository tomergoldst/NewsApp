package com.tomergoldst.newsapp.data.remote

import com.tomergoldst.newsapp.model.NewsArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface DiscoverNewsArticlesService {

    @GET("top-headlines")
    fun discoverTopNewsArticles(@QueryMap params: Map<String, String>): Call<NewsArticlesResponse>

}