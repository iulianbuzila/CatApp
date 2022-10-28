package com.example.catapp.ui.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.catapp.extensions.observe
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseFragment<VB: ViewBinding, VM : BaseViewModel> : Fragment() {
    private lateinit var viewModel: VM
    protected var binding: VB? = null

    abstract fun getVM(): VM
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
    abstract fun VB.onViewCreated(savedInstanceState: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = bindingInflater(inflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getVM()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            observe(progressLiveEvent) { show ->
                if (show) {
                    (activity as BaseActivity<*, *>).showProgress()
                } else {
                    (activity as BaseActivity<*, *>).hideProgress()
                }
            }

            observe(errorMessage) { msg ->
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        }
        binding?.onViewCreated(savedInstanceState)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    protected fun navigate(navDirections: NavDirections, navOptions: NavOptions? = null) {
        val navController = findNavController()
        navController.currentDestination?.getAction(navDirections.actionId)?.run {
            navController.navigate(navDirections, navOptions)
        }
    }
}