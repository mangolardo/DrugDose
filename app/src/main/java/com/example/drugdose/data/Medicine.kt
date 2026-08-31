package com.example.drugdose.data

import androidx.room3.ColumnTypeConverters
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import kotlinx.serialization.Serializable


//data class Medicine(
//
//
//    val name : String,
//    val unit : String,
//    val mgPerUnit : Double,
//    val maxAge : Int,
//    val minAge : Int,
//    val maxWeight : Double,
//    val minWeight : Double,
//    val maxDose : Double?,
//    val dosages : List<Dosage>,
//    val pregnantOk : Boolean?
//
//)
@Serializable
@Entity(tableName = "Medicines")
@ColumnTypeConverters(Converter::class)
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String,
    val desc : String,
    val pregnantOk : Boolean?,
    val dosages : List<Dosage>,
    val dosageRules : List<DosageRule>
)

//@Serializable
//data class Medicine(
//    val name : String,
//    val unit : String,
//    val mgPerUnit : Double,
//    val maxAge : Int?,
//    val minAge : Int?,
//    val maxWeight : Double?,
//    val minWeight : Double?,
//    val maxDose : Double?,
//    val dosages : List<Dosage>,
//    val pregnantOk : Boolean?
//
//)