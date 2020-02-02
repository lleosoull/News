package com.lab.news.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lab.news.R
import com.lab.news.adapter.NewsAdapter
import com.lab.news.dao.NewsDataBase
import com.lab.news.repository.NewsRepository
import com.lab.news.util.messageUtils
import com.lab.news.viewModel.NewsViewModel
import com.lab.news.viewModel.factory.NewsViewModelFactory
import kotlinx.android.synthetic.main.news_fragment.*

class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private val adapterNews by lazy {
        NewsAdapter()
    }

    private val viewModel by lazy {
        val repository = NewsRepository(NewsDataBase.getInstance(context!!).newsDao)
        val factory = NewsViewModelFactory(repository)
        val provider = ViewModelProviders.of(this, factory)
        provider.get(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val manager = LinearLayoutManager(context)
        with(rvNews) {
            adapter = adapterNews
            layoutManager = manager
            setHasFixedSize(true)
        }
        refreshNews.setOnRefreshListener { searchNews() }
        searchNews()
    }

    private fun searchNews() {
        viewModel.searchNews().observe(this, Observer { resource ->
            resource.dado?.let {
                adapterNews.update(it)
                refreshNews.isRefreshing = false
            }
            resource.error?.let {
                messageUtils(layoutNews, R.string.error_api)
                refreshNews.isRefreshing = false
            }
        })
    }
}
