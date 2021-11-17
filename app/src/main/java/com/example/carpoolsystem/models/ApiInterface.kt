package com.example.carpoolsystem.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("vehicles/GetMakesForVehicleType/car")
    fun getCarMake(@Query("format") format: String): Call<VehicleCarMake>

    @GET("vehicles/getmodelsformake/{car_make}")
    fun getCarModel(
        @Path("car_make") carMake: String,
        @Query("format") format: String
    ): Call<VehicleCarModel>
}
