package com.example.netti2020

data class TrainsItem(
    val departureDate: String,
    val location: Location,
    val speed: Int,
    val timestamp: String,
    val trainNumber: Int
)