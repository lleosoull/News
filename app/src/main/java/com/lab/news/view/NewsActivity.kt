package com.lab.news.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lab.news.R

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NewsFragment.newInstance())
                .commitNow()
        }
    }

}
