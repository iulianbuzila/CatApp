package com.example.catapp.ui.cats.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.catapp.databinding.ItemViewHolderCatBinding
import com.example.catapp.models.CatModel

class CatsAdapter(
    private val onItemClick: (CatModel) -> Unit
) : ListAdapter<CatModel, CatViewHolder>(CatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding = ItemViewHolderCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item, onItemClick)
    }
}