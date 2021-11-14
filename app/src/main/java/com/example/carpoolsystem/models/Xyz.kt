package com.example.carpoolsystem.models
//
//data class Xyz(
//    val alcohol: String,
//    val blg: String,
//    val brand: String,
//    val hop: String,
//    val ibu: String,
//    val id: Int,
//    val malts: String,
//    val name: String,
//    val style: String,
//    val uid: String,
//    val yeast: String
//)


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}