package com.example.drugdose.data

import androidx.compose.ui.text.font.FontWeight
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "Profiles")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val age:Int,
    val weight: Float,
    val height: Float,
    val pregnant: Boolean
)
