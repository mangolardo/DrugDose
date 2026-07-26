package com.example.drugdose.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "Dosages")
data class Dosage(
    @PrimaryKey(autoGenerate = true)
    val type : String,
    val dose : Int
)