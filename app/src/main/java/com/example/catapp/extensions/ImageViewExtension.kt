package com.example.catapp.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadWithUri(uri: String?) {
    if (uri != null) {
        Glide
            .with(this.context)
            .load(uri)
            .into(this)
    } else {
        this.setImageDrawable(null)
    }
}