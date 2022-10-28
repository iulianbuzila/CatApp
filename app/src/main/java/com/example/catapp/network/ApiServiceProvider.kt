package com.example.catapp.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceProvider {

    private const val BASE_URL = "https://api.thecatapi.com/v1/"

    fun getClient(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
            .create(ApiService::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val basicInterceptor = Interceptor { chain ->
            val original = chain.request()

            val newRequest = original
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", "live_PL1Zgync2YmwBaTNJnNTpoqGOCcBBiGc6HQA37WafzJFQKBO3smFpQH2nLrcSQEe")
                .build()
            chain.proceed(newRequest)
        }

        return OkHttpClient.Builder()
            .addInterceptor(basicInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}