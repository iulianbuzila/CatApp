package com.example.catapp.ui.cats.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.databinding.ItemViewHolderCatBinding
import com.example.catapp.extensions.loadWithUri
import com.example.catapp.models.CatModel

class CatViewHolder(private val binding: ItemViewHolderCatBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cat: CatModel, onItemClick: (CatModel) -> Unit) {
        binding.catImageView.loadWithUri(cat.image?.url)
        binding.catNameTextView.text = cat.name
        binding.catDescriptionTextView.text = cat.description

        binding.root.setOnClickListener {
            onItemClick.invoke(cat)
        }
    }
}