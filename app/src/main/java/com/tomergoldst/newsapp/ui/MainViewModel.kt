package com.tomergoldst.newsapp.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tomergoldst.newsapp.R
import com.tomergoldst.newsapp.data.DataSource
import com.tomergoldst.newsapp.data.remote.Constants
import com.tomergoldst.newsapp.model.NewsArticle
import com.tomergoldst.newsapp.utils.NetworkUtils
import java.util.*

class MainViewModel(
    application: Application,
    private val repository: DataSource
) :
    AndroidViewModel(application) {

    private val mNewsArticles: MutableLiveData<MutableList<NewsArticle>> = MutableLiveData()

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() =_dataLoading

    private val _hasNetworkConnection = MutableLiveData<Boolean>()
    val hasNetworkConnection: LiveData<Boolean>
        get() =_hasNetworkConnection

    private val mContext: Context = getApplication()

    init {
        _dataLoading.value = true

        if (NetworkUtils.hasNetworkConnection(mContext)) {
            _hasNetworkConnection.value = true
            discoverNewsArticles()
        } else {
            _dataLoading.value = false
            _hasNetworkConnection.value = false
        }
    }

    fun refresh(){
        _dataLoading.value = true
        discoverNewsArticles()
    }

    private fun discoverNewsArticles() {
        repository.getNewsArticles(getQueryParams(), object : DataSource.LoadNewsArticlesCallback{
            override fun onNewsArticlesLoaded(newsArticles: List<NewsArticle>) {
                mNewsArticles.value = newsArticles.toMutableList()

                if (_dataLoading.value == true) {
                    _dataLoading.value = false
                }
            }

            override fun onDataNotAvailable() {
                _dataLoading.value = false

            }
        })

    }

    private fun getQueryParams(): MutableMap<String, String> {
        val queryParams: MutableMap<String, String> = HashMap()
        queryParams[Constants.API_KEY] = mContext.getString(R.string.news_api_key)
        queryParams[Constants.SOURCES] = Constants.SOURCE_TECHCRUNCH
        return queryParams
    }

    fun getNewsArticles(): LiveData<MutableList<NewsArticle>> {
        return mNewsArticles
    }

}