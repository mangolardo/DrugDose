package com.example.drugdose.data


import androidx.room3.vo.Entity
import kotlinx.serialization.Serializable

@Entity(tableName = "Dosages")
data class Dosage(
    @PrimaryKey(autogenerate = true)
    val type : String,
    val dose : Int
)