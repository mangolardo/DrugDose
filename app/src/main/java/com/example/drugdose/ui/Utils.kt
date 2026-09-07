package com.example.drugdose.ui

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.text.input.TextFieldState
import androidx.core.text.isDigitsOnly
import com.example.drugdose.data.Dosage
import com.example.drugdose.data.Medicine
import com.example.drugdose.data.Profile
import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.reflect.typeOf

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

//fun validateInput(state : TextFieldState, label : String) : Boolean{
//    return if(state.text == "") true else
//        if (label == "Age"){
//            (state.text.toString().toIntOrNull() == null || state.text.toString().toInt() <= 0 ||state.text.toString().toInt() > 100)
//        }else if(label.contains("Weight")){
//            (state.text.toString().toShortOrNull() == null || state.text.toString().toShort() <= 0|| state.text.toString().toShort() > 230)
//        } else {
//            (state.text.toString().toShortOrNull() == null || state.text.toString().toShort() <= 70 || state.text.toString().toShort() > 220)
//        }
//}
fun validateInputEmpty(state : TextFieldState, label : String) : Boolean{
    return if(state.text == "") false else
        if (label == "Age"){
            if( state.text.isDigitsOnly()){
            (state.text.toString().toInt() !in 1..100)} else {
                true
            }
        }else if(label.contains("Weight")){
            if( state.text.isDigitsOnly()){
            (state.text.toString().toFloat() !in 1.0..230.0)} else {
                true
            }
        } else {
            if( state.text.isDigitsOnly()){
            (state.text.toString().toInt() !in 60..220)} else {
                true
            }
        }
}

fun pluralize(string : String,count: Double) : String {
    var res : String = string
    if(count > 1.0) {
        when (string) {
            "Packet" -> {
                res = "Packets"

            }

            "Tablet" -> {
                res = "Tablets"

            }
            "Suppository" -> {
                res = "Suppositories"

            }
        }
    }
    return res
}