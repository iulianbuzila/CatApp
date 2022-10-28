package com.example.catapp.ui

import androidx.activity.viewModels
import com.example.catapp.databinding.MainActivityBinding
import com.example.catapp.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>(MainActivityBinding::inflate) {

    private val viewModel : MainViewModel by viewModels()

    override fun getVM(): MainViewModel = viewModel
}