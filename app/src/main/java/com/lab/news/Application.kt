package com.lab.news

import android.app.Application

class Application : Application() {

    companion object {
        lateinit var aplication: Application
    }

    override fun onCreate() {
        super.onCreate()
        aplication = this
    }
}