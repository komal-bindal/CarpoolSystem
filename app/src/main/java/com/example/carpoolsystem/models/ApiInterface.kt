package com.example.carpoolsystem.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("vehicles/getallmakes")
    fun getCarMake(@Query("format") format: String): Call<Abc>

}


//@Query("format") condition: String)
//    @GET("api/beer/random_beer")
//    fun getData(): Call<List<Xyz>>

//@Query("format") condition: String