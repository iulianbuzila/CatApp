package com.example.catapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class CatImageModel (
    @SerializedName("id") val id: String? = null,
    @SerializedName("url") val url: String? = null,
): Parcelable