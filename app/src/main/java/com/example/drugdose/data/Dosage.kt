package com.example.drugdose.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Dosage")
data class Dosage(
    val type : String,
    val dose : Double
)