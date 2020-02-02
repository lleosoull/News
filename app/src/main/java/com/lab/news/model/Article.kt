package com.lab.news.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "article")
class Article {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @Ignore
    var source: Source? = null

    var author: String? = null
    var title: String? = null
    var descriptor: String? = null
    var url: String? = null
    var urlToImage: String? = null
    var publishedApi: String? = null
    var content: String? = null

}