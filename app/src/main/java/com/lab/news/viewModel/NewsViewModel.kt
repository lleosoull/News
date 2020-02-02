package com.lab.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lab.news.model.Article
import com.lab.news.repository.NewsRepository
import com.lab.news.repository.Resource

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    fun searchNews(): LiveData<Resource<List<Article>?>> {
        return repository.searchAllInformation()
    }
}
