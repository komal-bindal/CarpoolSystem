package com.example.carpoolsystem.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("vehicles/GetMakesForVehicleType/car")
    fun getCarMake(@Query("format") format: String): Call<Vehicle>

}
