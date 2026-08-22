package com.example.drugdose.ui

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.text.input.TextFieldState
import com.example.drugdose.data.Dosage
import com.example.drugdose.data.Medicine
import com.example.drugdose.data.Profile
import kotlin.math.abs
import kotlin.math.sqrt

//write utils to parse and search JSON

//assuming dose is in milligrams
fun calculateDose (profile: Profile, medObj : Medicine ):Double {
//check for max dose

    //check min/max age,weight
    val age = profile.age
    val weight = profile.weight
    val height = profile.height
    var dose  : Double
    if(medObj.unit == "kg"){
      dose =   calculateOnKg(medObj.mgPerUnit,weight.toDouble())
    }
    else if(medObj.unit == "m2") {
        dose = calculateOnM2(medObj.mgPerUnit, height.toInt(), weight.toDouble())
    } else {
        throw Exception("wrong med format")
    }

    return dose
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
fun validateInput(state : TextFieldState, label : String) : Boolean{
    return if(state.text == "") true else
        if (label == "Age"){
            (state.text.toString().toIntOrNull() == null || state.text.toString().toInt() <= 0 ||state.text.toString().toInt() > 110)
        }else if(label.contains("Weight")){
            (state.text.toString().toShortOrNull() == null || state.text.toString().toShort() <= 0|| state.text.toString().toShort() > 230)
        } else {
            (state.text.toString().toShortOrNull() == null || state.text.toString().toShort() <= 70 || state.text.toString().toShort() > 220)
        }
}
fun validateInputEmpty(state : TextFieldState, label : String) : Boolean{
    return if(state.text == "") false else
        if (label == "Age"){
            (state.text.toString().toInt() !in 1..110)
        }else if(label.contains("Weight")){
            (state.text.toString().toShort() !in 1..230)
        } else {
            (state.text.toString().toShort() !in 71..220)
        }
}
