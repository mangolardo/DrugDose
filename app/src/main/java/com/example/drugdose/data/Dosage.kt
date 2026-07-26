package com.example.drugdose.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Dosage(
    val type : String,
    val dose : Int
)