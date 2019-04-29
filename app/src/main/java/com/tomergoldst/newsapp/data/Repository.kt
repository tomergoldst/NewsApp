package com.tomergoldst.newsapp.data

import com.tomergoldst.newsapp.model.NewsArticle

class Repository private constructor(
    private val newsArticlesRemoteDataSource: DataSource
) : DataSource {

    companion object {

        private val TAG = Repository::class.java.simpleName

        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(
            newsArticlesRemoteDataSource: DataSource
        ):
                Repository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Repository(newsArticlesRemoteDataSource).also { INSTANCE = it }
            }

    }

    override fun getNewsArticles(queryParams: Map<String, String>, callback: DataSource.LoadNewsArticlesCallback) {
        newsArticlesRemoteDataSource.getNewsArticles(queryParams, object : DataSource.LoadNewsArticlesCallback {
            override fun onNewsArticlesLoaded(newsArticles: List<NewsArticle>) {
                callback.onNewsArticlesLoaded(newsArticles)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

}
