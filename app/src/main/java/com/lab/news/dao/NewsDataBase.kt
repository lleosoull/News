package com.lab.news.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lab.news.model.Article

@Database(entities = [Article::class], version = 1)
abstract class NewsDataBase : RoomDatabase() {

    abstract val newsDao: NewsDAO

    companion object {
        private lateinit var db: NewsDataBase

        fun getInstance(context: Context): NewsDataBase {
            if (::db.isInitialized) return db

            db = Room.databaseBuilder(context, NewsDataBase::class.java, "lleosoull").build()
            return db
        }
    }
}