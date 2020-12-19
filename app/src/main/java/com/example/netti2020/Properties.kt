package com.example.netti2020

data class Properties(
    val locode: String,
    val name: String,
    val nationality: String,
    val portRestrictions: List<PortRestriction>,
    val seaArea: String
)