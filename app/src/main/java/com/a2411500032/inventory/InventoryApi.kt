package com.a2411500032.inventory

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.GET

// 1. INTERFACE API
interface InventoryApi {
    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("username") user: String,
        @Field("password") pass: String
    ): Call<ResponseModel>

    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("username") user: String,
        @Field("password") pass: String
    ): Call<ResponseModel>

    @GET("barang.php")
    fun getBarang(): Call<BarangResponse>
}

// 2. MODEL UNTUK LOGIN & REGISTER (Sesuai dengan function response() di PHP kamu)
data class ResponseModel(
    val status: String,
    val message: String,
    val data: UserData? // Karena PHP mengirim data user setelah login
)

data class UserData(
    val id_user: String,
    val username: String
)

// 3. MODEL KHUSUS UNTUK DAFTAR BARANG
data class BarangResponse(
    val status: String,
    val message: String,
    val data: List<BarangModel>
)

data class BarangModel(
    val id: String,
    val nama_barang: String,
    val stok: String,
    val harga: String,
    val deskripsi: String?
)