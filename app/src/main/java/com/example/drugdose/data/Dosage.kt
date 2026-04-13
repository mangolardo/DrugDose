package com.example.drugdose.data

import kotlinx.serialization.Serializable

@Serializable
data class Dosage(
    val type : String,
    val dose : Int
)