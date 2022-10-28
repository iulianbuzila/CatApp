package com.example.catapp.data.repository

import com.example.catapp.data.remotedatasource.CatRemoteDataSource
import com.example.catapp.models.CatModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val catRemoteDataSource: CatRemoteDataSource,
) {
    fun fetchCats(): Single<List<CatModel>> {
        return catRemoteDataSource.fetchCats()
    }
}