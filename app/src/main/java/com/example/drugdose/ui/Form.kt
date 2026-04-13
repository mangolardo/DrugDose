package com.example.drugdose.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.drugdose.data.Medicine
import kotlinx.serialization.json.Json

//visualize standard form. Get user input to calculate dose and alerts based on selected medicine
// maybe create method to generate custom form for selected med, based on med profile (ex. med not recc for pregnant subjects, add pregnancy radio button)
// OR calculate alerts based on med BEFORE form
class Form : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = this
        val medId = intent.getStringExtra("MedId")!!
        val med = Json.decodeFromString<Medicine>(medId)
       // createAlert(medId) //alert per controindicazioni
        setContent {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = medId)
                val weightState = rememberTextFieldState()
                TextField(
                    state = weightState,
                    label = { Text(text="Weight(kg)")}
                )
                val heightState = rememberTextFieldState()
                TextField(
                    state = heightState,
                    label = { Text(text="Height(cm)")}
                )
                val ageState = rememberTextFieldState()
                TextField(
                    state = ageState,
                    label = { Text(text="Age")}
                )
                val fields = mapOf( "Weight" to weightState.text.toString(), "Height" to heightState.text.toString(), "Age" to ageState.text.toString() )


                Button(onClick = {
                    val result = calculateDose(fields, med)
                    val converted = Json.encodeToString(convertToCommercial(result.split(",").last().toDouble(), med))

                            // switchAct(activity,Result::class.java)
                    val intent = Intent(activity, Result::class.java)
                    intent.putExtra("commercial",converted)
                    intent.putExtra("dose", result)
                    intent.putExtra("name", med.name)
//                    intent.putExtra("pregnantOk", med.pregnantOk )


                    activity.startActivity(intent)
                })
                { Text(text = "Confirm") }
            }
        }
    }
}
//is activity