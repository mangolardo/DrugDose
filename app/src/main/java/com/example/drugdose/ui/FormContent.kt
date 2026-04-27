package com.example.drugdose.ui

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldLabelPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.drugdose.Form
import com.example.drugdose.Result
import com.example.drugdose.calculateDose
import com.example.drugdose.convertToCommercial
import com.example.drugdose.data.Medicine
import com.example.drugdose.ui.components.FormField
import kotlinx.serialization.json.Json

@Composable
fun FormContent(med : Medicine, activity : ComponentActivity, viewModel: Form.formViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),

        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(text = "Form for " + med.name)
        val weightState = viewModel.weightState
        FormField(
            state = weightState,
            label = "Weight(kg)"
        )
        val heightState = viewModel.heightState
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
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(25) ,
            onClick = {
            val result = calculateDose(fields, med)
            val converted = Json.Default.encodeToString(
                convertToCommercial(
                    result.split(",").last().toDouble(), med
                )
            )

            // switchAct(activity,Result::class.java)
            val intent = Intent(activity, Result::class.java)
            intent.putExtra("commercial", converted)
            intent.putExtra("dose", result)
            intent.putExtra("name", med.name)
//                    intent.putExtra("pregnantOk", med.pregnantOk )


            activity.startActivity(intent)
        })
        { Text(text = "Confirm") }
    }

}