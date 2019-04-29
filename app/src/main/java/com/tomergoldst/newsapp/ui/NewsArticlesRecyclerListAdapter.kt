package com.tomergoldst.newsapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.tomergoldst.newsapp.utils.ReadableDateUtils
import com.tomergoldst.newsapp.R
import com.tomergoldst.newsapp.config.GlideApp
import com.tomergoldst.newsapp.model.NewsArticle

class NewsArticlesRecyclerListAdapter(
    val mListener: OnAdapterInteractionListener
) :
    ListAdapter<NewsArticle, NewsArticlesRecyclerListAdapter.NewsArticleViewHolder>(DiffCallback()) {

    companion object {
        val TAG: String = NewsArticlesRecyclerListAdapter::class.java.simpleName

    }

    interface OnAdapterInteractionListener {
        fun onItemClicked(newsArticle: NewsArticle)
    }

    inner class NewsArticleViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val articleImageView: ImageView = v.findViewById(R.id.articleImageView)
        private val articleTitleTxv: TextView = v.findViewById(R.id.articleTitleTxv)
        private val articlePublishedAtTxv: TextView = v.findViewById(R.id.articlePublishedAtTxv)
        private val articleDescriptionTxv: TextView = v.findViewById(R.id.articleDescriptionTxv)

        fun bind(newsArticle: NewsArticle) {
            val context = itemView.context

            articleTitleTxv.text = newsArticle.title
            articlePublishedAtTxv.text =
                ReadableDateUtils.getDateTimeWithDayOfWeekFormat(context, newsArticle.publishedAt.time)
            articleDescriptionTxv.text = newsArticle.description

            itemView.setOnClickListener {
                mListener.onItemClicked(newsArticle)
            }

            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.apply {
                strokeWidth = 5f
                centerRadius = 60f
                start()
            }

            GlideApp.with(context)
                .load(newsArticle.urlToImage)
                .placeholder(circularProgressDrawable)
                .into(articleImageView)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsArticleViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news_article, parent, false)
        return NewsArticleViewHolder(v)
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        holder.bind(getItem(holder.adapterPosition))
    }

    class DiffCallback : DiffUtil.ItemCallback<NewsArticle>() {
        override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
            return (oldItem.url == newItem.url)
        }

        override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
            return oldItem == newItem
        }
    }

}
