package com.lab.news.util

import android.view.View
import android.widget.ImageView
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.lab.news.Application


fun imageUtils(url: String?, view: ImageView, centerCrop: Boolean = true) {
    val builder = Glide.with(Application.aplication)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
    if (centerCrop) {
        builder.centerCrop().into(view)
    } else {
        builder.into(view)
    }
}

fun messageUtils(view: View, @StringRes text: Int) {
    Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
}