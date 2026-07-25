package com.example.drugdose.data

import androidx.compose.ui.text.font.FontWeight
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val name: String,
    val age:Int,
    val weight: Short,
    val height:Short,
    val pregnant: Boolean
)
