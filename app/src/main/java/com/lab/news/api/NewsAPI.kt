package com.lab.news.api

import com.lab.news.dtl.NewsDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val COUNTRY: String = "br"
private const val API_KEY: String = "559b72db899c43eeb21738149d216fc7"

interface NewsAPI {

    //https://newsapi.org/v2/top-headlines?country=br&apiKey=559b72db899c43eeb21738149d216fc7

    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String = COUNTRY,
        @Query("apiKey") apiKey: String = API_KEY
    ): Call<NewsDTO>
}