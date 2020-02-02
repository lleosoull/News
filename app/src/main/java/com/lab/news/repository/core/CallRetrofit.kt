package com.lab.news.repository.core

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class CallRetrofit {

    fun <T> runCall(
        call: Call<T>,
        success: (resource: T?) -> Unit,
        failure: (error: String?) -> Unit
    ) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    success(response.body())
                } else {
                    failure(call.toString())
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                failure(t.message)
            }
        })
    }
}