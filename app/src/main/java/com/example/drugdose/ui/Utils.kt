package com.example.drugdose.ui

import android.app.Activity
import android.content.Intent
import com.example.drugdose.data.Dosage
import com.example.drugdose.data.Medicine
import kotlin.math.abs
import kotlin.math.sqrt

//write utils to parse and search JSON

//assuming dose is in milligrams
fun calculateDose (fields : Map<String,String>, medObj : Medicine ):String {
//check for max dose

    //check min/max age,weight
    val result = mutableListOf<String>()
    val age = fields.getValue("Age")
    val weight = fields.getValue("Weight")
    val height = fields.getValue("Height")
    var dose  : Double

    if(age.toInt() > medObj.maxAge){
        result.add("MaxAge")
    } else if(age.toInt() < medObj.minAge) {result.add("MinAge")}
    if(weight.toDouble() > medObj.maxWeight){
        result.add("MaxWeight")
    } else if(weight.toDouble() < medObj.minWeight) {result.add("MinWeight")}

    if(medObj.unit == "kg"){
        dose = calculateOnKg(medObj.mgPerUnit,weight.toDouble())
    } else {
        dose = calculateOnM2(medObj.mgPerUnit, height.toInt(), weight.toDouble())}

    if(medObj.maxDose != null && dose > medObj.maxDose) { dose = medObj.maxDose}
    result.add(dose.toString())

    return result.joinToString(",")
}
fun calculateOnKg(dose : Double, w : Double) : Double {
return dose*w
}
fun calculateOnM2(dose : Double, h : Int, w : Double): Double {
    val bsa = sqrt(h * w / 3600)
return dose * bsa
}

fun convertToCommercial(dose : Double, med : Medicine):Dosage{
    //fix this
    val iter = med.dosages.listIterator(1)
    var diff = abs(dose - med.dosages[0].dose)
    var closest : Dosage = med.dosages[0]
    while (iter.hasNext()) {
        val commDose = iter.next()
        val curr = abs(dose - commDose.dose)
         if( curr < diff)
         { diff = curr
         closest = commDose}
    }
    return closest
}
//method do convert dose in actual commercial dosage
