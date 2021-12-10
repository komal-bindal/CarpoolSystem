package com.example.carpoolsystem.models

data class Request(
    var driverId: String,
    var driverName: String,
    var passengerId: String,
    var passengerName: String,
    var source: String,
    var destination: String,
    var date: String,
    var time: String,
    var status: String
)
