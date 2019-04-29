package com.tomergoldst.newsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tomergoldst.newsapp.R
import com.tomergoldst.newsapp.model.NewsArticle
import com.tomergoldst.newsapp.utils.DimensionUtils
import com.tomergoldst.newsapp.utils.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    NewsArticlesRecyclerListAdapter.OnAdapterInteractionListener {

    private lateinit var mModel: MainViewModel
    private lateinit var mAdapter: NewsArticlesRecyclerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()

        mModel = ViewModelProviders.of(this, InjectorUtils.getMainViewModelProvider(application))
            .get(MainViewModel::class.java)

        mModel.getNewsArticles().observe(this, Observer {
            mAdapter.submitList(it)
        })

        mModel.dataLoading.observe(this, Observer {
            if (it) contentLoadingProgressBar.show() else contentLoadingProgressBar.hide()
            recyclerView.isVisible = !it
        })

        mModel.hasNetworkConnection.observe(this, Observer {
            if (!it){
                Toast.makeText(this@MainActivity, "No network connection", Toast.LENGTH_SHORT).show()
            }
        })

        mAdapter = NewsArticlesRecyclerListAdapter(this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                SimpleItemDecoration(
                    DimensionUtils.dp2Px(context, 8).toInt(),
                    DimensionUtils.dp2Px(context, 8).toInt()
                )
            )
            adapter = mAdapter

        }

        contentLoadingProgressBar.show()

        if (getString(R.string.news_api_key) == "YOUR_NEWSAPI_KEY_HERE"){
            Toast.makeText(this, "Missing newapi.org api key", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()
        mModel.refresh()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.title = getString(com.tomergoldst.newsapp.R.string.app_name)
    }

    override fun onItemClicked(newsArticle: NewsArticle) {
        startActivity(Intent(this, NewsArticleActivity::class.java)
            .also { it.putExtra(NewsArticleActivity.EXTRA_URL, newsArticle.url) }
            .also { it.putExtra(NewsArticleActivity.EXTRA_TITLE, newsArticle.title) }
        )
    }

}
