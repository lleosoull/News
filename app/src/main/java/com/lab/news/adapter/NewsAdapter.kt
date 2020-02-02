package com.lab.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lab.news.R
import com.lab.news.holder.NewsHolder
import com.lab.news.model.Article

class NewsAdapter(
    private val article: MutableList<Article> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun update(article: List<Article>) {
        this.article.addAll(article)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.inflate_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsHolder).build(article[position])
    }

    override fun getItemCount(): Int {
        return article.size
    }
}