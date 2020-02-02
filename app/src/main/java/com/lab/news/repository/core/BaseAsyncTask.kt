package com.lab.news.repository.core

import android.os.AsyncTask

class BaseAsyncTask<T>(
    private val backGround: () -> T,
    private val postExecute: (result: T?) -> Unit
) : AsyncTask<Void, Void, T?>() {

    override fun doInBackground(vararg params: Void?): T = backGround()

    override fun onPostExecute(result: T?) {
        super.onPostExecute(result)
        postExecute(result)
    }
}