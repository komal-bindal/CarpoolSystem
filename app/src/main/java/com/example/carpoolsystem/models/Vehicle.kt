package com.example.carpoolsystem.models

data class Vehicle(
    val Count: Int,
    val Message: String,
    val Results: List<CarMake>,
    val SearchCriteria: String
)