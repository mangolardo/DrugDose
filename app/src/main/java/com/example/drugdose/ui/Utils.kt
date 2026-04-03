package com.example.drugdose.ui

import android.app.Activity
import android.content.Intent

    fun  switchAct(activity : Activity, nextClass : Class<*>){
        val intent = Intent(activity, nextClass)
        activity.startActivity(intent)
    }
fun switchToForm(activity : Activity, medId : String){
    val intent = Intent(activity, Form::class.java)
    intent.putExtra("MedId", medId)
    activity.startActivity(intent)
}

