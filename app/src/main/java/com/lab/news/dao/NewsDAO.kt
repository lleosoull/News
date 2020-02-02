package com.lab.news.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lab.news.model.Article

@Dao
interface NewsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<Article>)

    @Query("SELECT * FROM article")
    fun list(): LiveData<List<Article>>
}