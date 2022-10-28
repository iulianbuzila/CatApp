package com.example.catapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.example.catapp.databinding.FragmentLoginBinding
import com.example.catapp.extensions.observe
import com.example.catapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun getVM(): LoginViewModel = viewModel
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun FragmentLoginBinding.onViewCreated(savedInstanceState: Bundle?) {
        observe(viewModel.loginSuccessfullyLiveData) {
            if (it) {
                navigateToCatsListScreen()
            }
        }

        observe(viewModel.invalidEmailLiveData) {
            emailTextInputLayout.error = it
        }

        observe(viewModel.invalidPasswordLiveData) {
            passwordTextInputLayout.error = it
        }

        emailEditText.doAfterTextChanged {
            emailTextInputLayout.error = null
        }

        passwordEditText.doAfterTextChanged {
            passwordTextInputLayout.error = null
        }

        loginButton.setOnClickListener {
            viewModel.onLoginButtonClicked(emailEditText.text.toString(), passwordEditText.text.toString())
        }
    }

    private fun navigateToCatsListScreen() {
        val loginToCatsFragmentDirection = LoginFragmentDirections.actionLoginFragmentToCatsFragment()
        navigate(loginToCatsFragmentDirection)
    }
}