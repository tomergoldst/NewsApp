package com.tomergoldst.newsapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.rule.ActivityTestRule
import com.tomergoldst.newsapp.ui.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tomergoldst.newsapp.ui.NewsArticleActivity
import com.tomergoldst.newsapp.ui.NewsArticlesRecyclerListAdapter

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class OpenNewsArticleUrlTest {

    @get:Rule
    val activityRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun whenClickOnArticle_goesToNewsArticleActivity() {
        // todo: delaying until resource is ready should be done properly with idling resource
        Thread.sleep(3000)

        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<NewsArticlesRecyclerListAdapter.NewsArticleViewHolder>(0, click()))

        Thread.sleep(1000)

        intended(hasComponent(NewsArticleActivity::class.java.name))

    }
}

