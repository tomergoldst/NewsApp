package com.tomergoldst.newsapp.data

import com.tomergoldst.newsapp.model.NewsArticle
import com.tomergoldst.newsapp.model.NewsArticleSource
import java.util.*
import kotlin.collections.ArrayList

class RemoteDataSourceMock : DataSource {

    override fun getNewsArticles(queryParams: Map<String, String>, callback: DataSource.LoadNewsArticlesCallback) {
        // Very simple list of news articles, consist of one item
        // In real life we will populate this with more realistic data
        val newsArticles: MutableList<NewsArticle> = ArrayList()
        newsArticles.add(
            NewsArticle(
                NewsArticleSource(null, "source"),
                "author",
                "title",
                "description",
                 "https://url",
                "https://urltoimage",
                Date(),
                "content"
            )
        )

        callback.onNewsArticlesLoaded(newsArticles)
    }
}