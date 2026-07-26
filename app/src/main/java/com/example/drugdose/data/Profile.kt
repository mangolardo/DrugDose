package com.example.drugdose.data

import androidx.compose.ui.text.font.FontWeight
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "Profiles")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    val name: String,
    val age:Int,
    val weight: Short,
    val height:Short,
    val pregnant: Boolean
)
