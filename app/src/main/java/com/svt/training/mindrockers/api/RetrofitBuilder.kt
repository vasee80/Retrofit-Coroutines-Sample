package com.svt.training.mindrockers.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder{

    private const val BASE_URL = "https://5efb024480d8170016f75db4.mockapi.io/svtapi/v1/"

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    /*fun <T> createService(service: T) : ApiService<T>{
        return getRetrofit().create(service::class.java)
    }*/
    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}