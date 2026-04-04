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
    //maybe make class for unit?? /m2 or /kg
    val maxDose : Float? = null,
    //Unit,Float
  //  val alerts : List<*>? = null ,//diff types of restrictions : ageRes, weightRes,pathologies, incompatibility
    val pregnantOk : Boolean? = null
)