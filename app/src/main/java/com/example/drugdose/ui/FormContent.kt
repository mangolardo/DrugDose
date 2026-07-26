package com.example.drugdose.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.drugdose.data.Medicine
import com.example.drugdose.ui.components.FormField
import com.example.drugdose.ui.theming.AppTheme
import kotlinx.serialization.json.Json
import kotlin.getValue
@Composable
fun Form(toMedList:Boolean, ontoMedList: () -> Unit, uiState : UiState) {
//might need to make viewmodel fields for the form
    val ageState = TextFieldState()
    val weightState  = TextFieldState()
    val heightState = TextFieldState()
    val med = uiState.selectedMed
        // createAlert(medId) //alert per controindicazioni

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(text = "Form for " + med!!.name)
        FormField(
            state = weightState,
            label = "Weight(kg)"
        )
        FormField(
            state = heightState,
            label = "Height(cm)"
        )
        val ageState = viewModel.ageState
        FormField(
            state = ageState,
            label = "Age"
        )
        val fields = mapOf(
            "Weight" to weightState.text.toString(),
            "Height" to heightState.text.toString(),
            "Age" to ageState.text.toString()
        )


        Button(
            //if ontoMedList save profile fields to viewmodel AND save profile to db
            //else save profile to db and go back profiles
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(25) ,
            onClick = {
//            val result = calculateDose(fields, med)
//            val converted = Json.Default.encodeToString(
//                convertToCommercial(
//                    result.split(",").last().toDouble(), med
//                )
//            )
//
//            // switchAct(activity,Result::class.java)
//            val intent = Intent(activity, Result::class.java)
//            intent.putExtra("commercial", converted)
//            intent.putExtra("dose", result)
//            intent.putExtra("name", med.name)
////                    intent.putExtra("pregnantOk", med.pregnantOk )



        })
        { Text(text = "Confirm") }
    }

}