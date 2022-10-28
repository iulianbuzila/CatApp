package com.example.catapp.ui.cats.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.catapp.models.CatModel

class CatDiffCallback : DiffUtil.ItemCallback<CatModel>() {

    override fun areItemsTheSame(oldItem: CatModel, newItem: CatModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CatModel, newItem: CatModel): Boolean {
        return when {
            oldItem.name != newItem.name -> { false }
            oldItem.description != newItem.description -> { false }
            else -> true
        }
    }
}
