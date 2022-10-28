package com.example.catapp.data.repository

import com.example.catapp.data.remotedatasource.LoginRemoteDataSource
import com.example.catapp.models.LoginResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource,
) {
    fun login(email: String, password: String): Single<LoginResponse> {
        return loginRemoteDataSource.loginUser(email, password).doOnSuccess {
            //save token in shared Pref
        }
    }
}
