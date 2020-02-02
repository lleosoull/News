package com.lab.news.dtl

import com.lab.news.model.Article

data class NewsDTO(
    var status: String,
    var totalResults: String,
    var articles: List<Article>
)