package com.example.carpoolsystem.models

data class Ride(
    var driverId: String,
    var name: String,
    var source: String,
    var destination: String,
    var date: String,
    var time: String
)