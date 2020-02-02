package com.lab.news.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.lab.news.api.AppRetrofit
import com.lab.news.api.NewsAPI
import com.lab.news.dao.NewsDAO
import com.lab.news.dtl.NewsDTO
import com.lab.news.model.Article
import com.lab.news.repository.core.BaseAsyncTask
import com.lab.news.repository.core.CallRetrofit

class NewsRepository(
    private val dao: NewsDAO
) : CallRetrofit() {

    private val mediator = MediatorLiveData<Resource<List<Article>?>>()
    private val service: NewsAPI = AppRetrofit().newsService

    fun searchAllInformation(): LiveData<Resource<List<Article>?>> {

        mediator.addSource(searchInternal()) {
            mediator.value = Resource(dado = it)
        }

        val failureWebClient = MutableLiveData<Resource<List<Article>?>>()
        mediator.addSource(failureWebClient) {
            val resourceCurrent = mediator.value
            val resourceNew: Resource<List<Article>?> = if (resourceCurrent != null) {
                Resource(dado = resourceCurrent.dado, error = resourceCurrent.error)
            } else {
                it
            }
            mediator.value = resourceNew
        }

        searchApi {
            failureWebClient.value = Resource(dado = null, error = it)
        }

        return mediator
    }

    private fun searchNews(
        success: (resource: NewsDTO?) -> Unit,
        failure: (error: String?) -> Unit
    ) {
        runCall(
            service.getNews(),
            success = success,
            failure = failure
        )
    }

    private fun searchInternal(): LiveData<List<Article>> {
        return dao.list()
    }

    private fun searchApi(failure: (error: String?) -> Unit) {
        searchNews(
            success = {
                if (it != null) {
                    saveDataBase(it.articles)
                } else {
                    failure(null)
                }
            },
            failure = failure
        )
    }

    private fun saveDataBase(article: List<Article>) {
        BaseAsyncTask(
            backGround = {
                dao.insert(article)
            },
            postExecute = {}
        ).execute()
    }
}