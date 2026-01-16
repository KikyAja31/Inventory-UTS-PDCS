package com.a2411500032.inventory

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.12/inventory/" // IP SEKARANG !!!

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    val instance: InventoryApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(InventoryApi::class.java)
    }
}