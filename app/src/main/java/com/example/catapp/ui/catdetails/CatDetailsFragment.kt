package com.example.catapp.ui.catdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.catapp.databinding.FragmentCatDetailsBinding
import com.example.catapp.extensions.loadWithUri
import com.example.catapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatDetailsFragment : BaseFragment<FragmentCatDetailsBinding, CatDetailsViewModel>() {

    private val viewModel: CatDetailsViewModel by viewModels()
    override fun getVM(): CatDetailsViewModel = viewModel
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCatDetailsBinding
        get() = FragmentCatDetailsBinding::inflate

    private val arguments by navArgs<CatDetailsFragmentArgs>()

    override fun FragmentCatDetailsBinding.onViewCreated(savedInstanceState: Bundle?) {
        image.loadWithUri(arguments.cat.image?.url)
        nameTextView.text = arguments.cat.name
        descriptionTextView.text = arguments.cat.description
        countryCodeTextView.text = arguments.cat.countryCode
        temperamentTextView.text = arguments.cat.temperament
        linkTextView.text = arguments.cat.wikipediaUrl
    }
}