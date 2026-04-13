package com.example.drugdose.data

import kotlinx.serialization.Serializable


@Serializable
data class Medicine(
    val name : String,
    val unit : String = "",
    val mgPerUnit : Double,
    val maxAge : Int,
    val minAge : Int,
    val maxWeight : Double,
    val minWeight : Double,
    //maybe make class for unit?? /m2 or /kg
    val maxDose : Double? ,
    val dosages : List<Dosage>,
    //Unit,Double
    val alerts : List<String>?,//diff types of restrictions : pathologies, incompatibility
    val pregnantOk : Boolean?

)