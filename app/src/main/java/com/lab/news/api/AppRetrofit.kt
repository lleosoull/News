package com.lab.news.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppRetrofit {

    private val url = "https://newsapi.org/v2/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val newsService: NewsAPI by lazy {
        retrofit.create(NewsAPI::class.java)
    }
}