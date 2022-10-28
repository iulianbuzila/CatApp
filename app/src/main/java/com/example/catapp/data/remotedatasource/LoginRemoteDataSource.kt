package com.example.catapp.data.remotedatasource

import com.example.catapp.models.LoginResponse
import com.example.catapp.network.ApiService
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) {
    // TODO Login api
    fun loginUser(email: String, password: String): Single<LoginResponse> {
        return Single.fromCallable {
            LoginResponse(accessToken = "Bearer AAAAAAA=")
        }.delay(2, TimeUnit.SECONDS)
    }
}