package com.example.catapp.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.ref.WeakReference
import javax.inject.Inject

class StringResource @Inject constructor(@ApplicationContext context: Context) {

    private val context: WeakReference<Context> = WeakReference(context)

    fun getString(stringId: Int): String {
        return context.get()?.getString(stringId).toString()
    }
}