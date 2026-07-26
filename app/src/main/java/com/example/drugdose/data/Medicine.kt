package com.example.drugdose.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(tableName = "Medicines")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
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