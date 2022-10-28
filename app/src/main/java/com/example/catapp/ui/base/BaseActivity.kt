package com.example.catapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.catapp.extensions.observe

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel>(val viewBinder: (LayoutInflater) -> VB) : AppCompatActivity() {
    private lateinit var viewModel: VM
    protected lateinit var binding : VB

    abstract fun getVM(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinder.invoke(layoutInflater)
        setContentView(binding.root)
        viewModel = getVM()

        with(viewModel) {
            observe(progressLiveEvent) { show ->
                if (show) {
                    showProgress()
                } else {
                    hideProgress()
                }
            }

            observe(errorMessage) { msg ->
                Toast.makeText(this@BaseActivity, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun showProgress() = BaseProgress().show(supportFragmentManager, PROGRESS)

    fun hideProgress() {
        supportFragmentManager.fragments.filterIsInstance<BaseProgress>().forEach { it.dismiss() }
    }

    companion object {
        private const val PROGRESS = "Progress"
    }
}