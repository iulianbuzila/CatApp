package com.example.catapp.data.remotedatasource

import com.example.catapp.models.CatModel
import com.example.catapp.network.ApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    fun fetchCats(): Single<List<CatModel>> = apiService.getCats()
}