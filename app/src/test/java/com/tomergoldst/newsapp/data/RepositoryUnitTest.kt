package com.tomergoldst.newsapp.data

import com.tomergoldst.newsapp.model.NewsArticle
import org.junit.Test

import org.junit.Assert.*

class RepositoryUnitTest {

    private val mRemoteDataSource: DataSource = RemoteDataSourceMock()
    private val mRepository = Repository.getInstance(mRemoteDataSource)

    /**
     * Very simple test to check that the repository logic works
     */
    @Test
    fun getNewsArticles_fromRemote_returnListOfArticles() {
        mRepository.getNewsArticles(mapOf(), object : DataSource.LoadNewsArticlesCallback{
            override fun onNewsArticlesLoaded(newsArticles: List<NewsArticle>) {
                assert(newsArticles.isNotEmpty())
            }

            override fun onDataNotAvailable() {

            }
        })
    }
}
