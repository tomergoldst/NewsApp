package com.tomergoldst.newsapp.utils

import android.app.Application
import com.tomergoldst.newsapp.data.Repository
import com.tomergoldst.newsapp.data.remote.DiscoverNewsArticlesService
import com.tomergoldst.newsapp.data.remote.NewsArticlesRemoteDataSource
import com.tomergoldst.newsapp.data.remote.RetrofitClient
import com.tomergoldst.newsapp.ui.ViewModelProvider
import retrofit2.Retrofit

// Inject classes needed for various Activities and Fragments.
object InjectorUtils {

    fun getMainViewModelProvider(application: Application): ViewModelProvider {
        return ViewModelProvider(application, getRepository())
    }

    private fun getRetrofit(): Retrofit {
        return RetrofitClient.retrofit!!
    }

    private fun getRepository(): Repository {
        val discoverNewsArticlesService = getRetrofit().create(DiscoverNewsArticlesService::class.java)
        return Repository.getInstance(NewsArticlesRemoteDataSource(discoverNewsArticlesService))
    }

}