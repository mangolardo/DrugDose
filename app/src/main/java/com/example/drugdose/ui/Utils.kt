package com.example.drugdose.ui

import android.app.Activity
import android.content.Intent
import com.example.drugdose.data.Dosage
import com.example.drugdose.data.Medicine
import com.example.drugdose.data.Profile
import com.example.drugdose.data.Unit
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
    if(medObj.unit == Unit.KG){
      dose =   calculateOnKg(medObj.mgPerUnit,weight.toDouble())
    }
    else if(medObj.unit == Unit.M2) {
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
