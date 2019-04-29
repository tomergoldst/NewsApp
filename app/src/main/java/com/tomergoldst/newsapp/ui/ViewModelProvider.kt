package com.tomergoldst.newsapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tomergoldst.newsapp.data.DataSource

class ViewModelProvider(
    private val application: Application,
    private val repository: DataSource
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application, repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }
}