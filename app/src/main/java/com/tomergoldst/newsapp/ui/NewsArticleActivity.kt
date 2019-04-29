package com.tomergoldst.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.tomergoldst.newsapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_news_article.*
import kotlinx.android.synthetic.main.activity_news_article.toolbar
import java.lang.RuntimeException


class NewsArticleActivity : AppCompatActivity(){

    companion object {
        const val EXTRA_URL = "extra_url"
        const val EXTRA_TITLE = "extra_title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_article)

        val title = intent.getStringExtra(EXTRA_TITLE)
        title?.let {
            initToolbar(title)
        } ?: throw RuntimeException("You must provide a title in the intent extra using the param 'EXTRA_TITLE'")

        val url = intent.getStringExtra(EXTRA_URL)
        url?.let {
            webview.loadUrl(url)
        } ?: throw RuntimeException("You must provide a url in the intent extra using the param 'EXTRA_URL'")

    }

    private fun initToolbar(title: String) {
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.title = title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }


}
