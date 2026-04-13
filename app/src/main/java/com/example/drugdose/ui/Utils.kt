package com.example.drugdose.ui

import android.app.Activity
import android.content.Intent
import com.example.drugdose.data.Medicine
import kotlinx.serialization.json.Json
import kotlin.math.sqrt

//write utils to parse and search JSON
    fun  switchAct(activity : Activity, nextClass : Class<*>){
        val intent = Intent(activity, nextClass)
        activity.startActivity(intent)
    }
fun switchToForm(activity : Activity, medId : String){
    val intent = Intent(activity, Form::class.java)
    intent.putExtra("MedId", medId)
    activity.startActivity(intent)
}
fun calculateDose (fields : Map<String,String>, med : String, context : Activity ):String {
    val medObj = Json.decodeFromString<Medicine>(med)
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

