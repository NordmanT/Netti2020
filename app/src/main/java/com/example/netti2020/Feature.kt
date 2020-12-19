package com.example.netti2020

data class Feature(
    val geometry: Geometry,
    val locode: String,
    val properties: Properties,
    val type: String
)