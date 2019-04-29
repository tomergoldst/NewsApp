package com.tomergoldst.newsapp.data

import com.tomergoldst.newsapp.model.NewsArticle

interface DataSource {

    interface LoadNewsArticlesCallback {
        fun onNewsArticlesLoaded(newsArticles: List<NewsArticle>)
        fun onDataNotAvailable()
    }

    fun getNewsArticles(queryParams: Map<String, String>, callback: LoadNewsArticlesCallback)

}
