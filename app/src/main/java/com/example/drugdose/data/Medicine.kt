package com.example.drugdose.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(tableName = "Medicines")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val unit : Unit,
    val mgPerUnit : Double,
    val maxAge : Int,
    val minAge : Int,
    val maxWeight : Double,
    val minWeight : Double,
    val maxDose : Double?,
    val dosages : List<Dosage>,
    val pregnantOk : Boolean?

)