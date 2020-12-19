package com.example.netti2020

data class PortRestriction(
    val formattedText: String,
    val isCurrent: Boolean,
    val issueTime: String,
    val lastModified: String,
    val portClosed: Boolean,
    val portRestricted: Boolean,
    val rawText: String,
    val validFrom: String,
    val validUntil: Any
)