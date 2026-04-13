package com.example.drugdose.data

import androidx.compose.ui.platform.LocalContext
import kotlinx.serialization.Serializable
import java.io.File
import java.io.InputStream


@Serializable
data class Medicine(
    val id: String,
    val name : String,
    val unit : String = "",
    val mgPerUnit : Double,
    val maxAge : Int,
    val minAge : Int,
    val maxWeight : Double,
    val minWeight : Double,
    //maybe make class for unit?? /m2 or /kg
    val maxDose : Double? = null,
    //Unit,Double
  //  val alerts : List<*>? = null ,//diff types of restrictions : pathologies, incompatibility
    val pregnantOk : Boolean? = null

)