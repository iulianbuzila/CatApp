package com.example.catapp.ui.cats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.catapp.databinding.FilterChipBinding
import com.example.catapp.databinding.FragmentCatsBinding
import com.example.catapp.extensions.observe
import com.example.catapp.ui.base.BaseFragment
import com.example.catapp.ui.cats.adapter.CatsAdapter
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatsFragment : BaseFragment<FragmentCatsBinding, CatsViewModel>() {

    private val viewModel: CatsViewModel by viewModels()
    override fun getVM(): CatsViewModel = viewModel
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCatsBinding
        get() = FragmentCatsBinding::inflate

    private val catsAdapter = CatsAdapter(
        onItemClick = { cat ->
            val catsFragmentToCatDetailDirection = CatsFragmentDirections.actionCatsFragmentToCatDetailFragment(cat = cat)
            navigate(catsFragmentToCatDetailDirection)
        }
    )

    override fun FragmentCatsBinding.onViewCreated(savedInstanceState: Bundle?) {
        binding?.catsRecyclerView?.adapter = catsAdapter

        observe(viewModel.catsList) { cats ->
            catsAdapter.submitList(cats)
        }

        observe(viewModel.catsFiltered) { cats ->
            catsAdapter.submitList(cats)
        }

        observe(viewModel.countriesLiveData) {
            createFilters(it)
        }

        if (viewModel.catsList.value == null) {
            viewModel.getCatsList()
        }

        filterChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            val filters = arrayListOf<String>()
            viewModel.checkedFilters.clear()
            checkedIds.forEach { chipId ->
                filters.add(group.findViewById<Chip>(chipId).text.toString())
                viewModel.checkedFilters.add(chipId)
            }
            viewModel.filterCats(filters)
        }
    }

    private fun createFilters(countries: List<String>) {
        binding?.filterChipGroup?.removeAllViews()
        for (countryCode in countries) {
            val chip = FilterChipBinding.inflate(layoutInflater).root
            chip.id = viewModel.getViewId(countryCode)
            chip.text = countryCode
            chip.isChecked = viewModel.checkedFilters.contains(chip.id)
            binding?.filterChipGroup?.addView(chip)
        }
    }
}