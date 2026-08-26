package com.example.drugdose.ui

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.drugdose.data.Dosage
import com.example.drugdose.ui.components.AlertBox
import kotlinx.serialization.json.Json
import java.math.RoundingMode

@Composable
fun Result(uiState: UiState, viewModel: SharedViewModel, onClickAct : () -> Unit ) {

                ResultContent(viewModel, onClickAct)
}
@Composable
fun ResultContent(viewModel: SharedViewModel,onClickAct : () -> Unit) {
    val uiState by viewModel._uiState.collectAsStateWithLifecycle()
    var isAlert = false
    val alerts = mutableListOf<String>()
    val profile = uiState.selectedProfile
    val med = uiState.selectedMed
    var dose : Double
    if(profile == null || med==null) {
        throw Exception("profile or med not selected")
    } else {
         dose = calculateDose(profile = profile, medObj = med)

    }
        val age = profile.age
        val weight = profile.weight

        if (age > med.maxAge) {
            alerts.add("MaxAge")
        } else if (age < med.minAge) {
            alerts.add("MinAge")
        }
        if (weight.toDouble() > med.maxWeight) {
            alerts.add("MaxWeight")
        } else if (weight.toDouble() < med.minWeight) {
            alerts.add("MinWeight")
        }
        val commercial  = convertToCommercial( (dose), med)

        if (med.maxDose != null && dose > med.maxDose) {
            dose = med.maxDose
        }

        val n = dose / commercial.dose
        if(!alerts.isEmpty()){
          isAlert = true
        }
        if (isAlert) {
            AlertBox(args = alerts, isMedWarning = true)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            Text(text = "Your Daily Dose Of ${med.name} Is:",modifier = Modifier.drawBehind {
                val strokeWidthPx = 1.dp.toPx()
                val verticalOffset = size.height - 2.sp.toPx()
                drawLine(
                    color = Color.Black,
                    strokeWidth = strokeWidthPx,
                    start = Offset(0f, verticalOffset),
                    end = Offset(size.width, verticalOffset),
                    pathEffect = pathEffect
                )
            })
            Spacer(modifier = Modifier.size(10.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(25),
                onClick =
                    {
                        onClickAct()
                    }
            ) {
                Text(
                    text = "$dose mg \n or about " + n.toBigDecimal().setScale(
                        1,
                        RoundingMode.HALF_DOWN
                    ) + " " + commercial.type + " of " + commercial.dose + "mg",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
