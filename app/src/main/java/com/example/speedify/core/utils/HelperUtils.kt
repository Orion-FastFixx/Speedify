package com.example.speedify.core.utils

import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.speedify.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun View.animateVisibility(isVisible: Boolean, duration: Long = 500) {
    ObjectAnimator
        .ofFloat(this, View.ALPHA, if (isVisible) 1f else 0f)
        .setDuration(duration)
        .start()
}

fun ImageView.setImageFromUrl(context: Context, url: String) {
    Glide
        .with(context)
        .load(url)
        .placeholder(R.drawable.image_loading_placeholder)
        .error(R.drawable.image_load_error)
        .into(this)
}

inline fun <reified T> fromJson(json: String): T {
    val gson = Gson()
    return gson.fromJson(json, object : TypeToken<T>() {}.type)
}