package com.example.carpoolsystem.models

data class VehicleCarModel(
    val Count: Int,
    val Message: String,
    val Results: List<CarModel>,
    val SearchCriteria: String
)