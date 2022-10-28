package com.example.catapp.network

import com.example.catapp.models.CatModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {
    @GET("breeds")
    fun getCats(): Single<List<CatModel>>
}