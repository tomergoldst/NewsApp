package com.tomergoldst.newsapp.data.remote

import com.tomergoldst.newsapp.data.DataSource
import com.tomergoldst.newsapp.model.NewsArticlesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsArticlesRemoteDataSource(private val discoverNewsArticlesService: DiscoverNewsArticlesService) :
    DataSource {

    override fun getNewsArticles(
        queryParams: Map<String, String>,
        callback: DataSource.LoadNewsArticlesCallback
    ){
        discoverNewsArticlesService.discoverTopNewsArticles(queryParams).enqueue(object : Callback<NewsArticlesResponse> {
            override fun onFailure(call: Call<NewsArticlesResponse>, t: Throwable) {
                callback.onDataNotAvailable()
            }

            override fun onResponse(
                call: Call<NewsArticlesResponse>,
                response: Response<NewsArticlesResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onNewsArticlesLoaded(response.body()?.articles!!)
                } else {
                    callback.onDataNotAvailable()
                }
            }
        })

    }

}