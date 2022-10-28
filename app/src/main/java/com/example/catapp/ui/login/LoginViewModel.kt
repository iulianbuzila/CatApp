package com.example.catapp.ui.login

import androidx.lifecycle.MutableLiveData
import com.example.catapp.R
import com.example.catapp.data.repository.LoginRepository
import com.example.catapp.extensions.applyDefaultSchedulers
import com.example.catapp.ui.base.BaseViewModel
import com.example.catapp.utils.StringResource
import com.example.catapp.utils.UserCredentialsValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val stringResource: StringResource
) : BaseViewModel() {

    val loginSuccessfullyLiveData: MutableLiveData<Boolean> = MutableLiveData()

    var invalidEmailLiveData = MutableLiveData<String>()
    var invalidPasswordLiveData = MutableLiveData<String>()

    fun onLoginButtonClicked(email: String, password: String) {
        var isAllFieldsValid = true
        if (!UserCredentialsValidator.isEmailValid(email)) {
            invalidEmailLiveData.postValue(stringResource.getString(R.string.lbl_invalid_email))
            isAllFieldsValid = false
        }
        if (!UserCredentialsValidator.isPasswordValid(password)) {
            invalidPasswordLiveData.postValue(stringResource.getString(R.string.lbl_invalid_password))
            isAllFieldsValid = false
        }

        if (!isAllFieldsValid) {
            return
        }

        addSubscription(loginRepository.login(email, password)
            .applyDefaultSchedulers()
            .doOnSubscribe { setIsLoading(true) }
            .doOnTerminate { setIsLoading(false) }
            .subscribeBy(
                onSuccess = {
                    loginSuccessfullyLiveData.postValue(true)
                },
                onError = {
                    setErrorMessage(stringResource.getString(R.string.lbl_something_went_wrong))
                }
            )
        )
    }
}