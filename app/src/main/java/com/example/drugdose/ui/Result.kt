package com.example.drugdose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.drugdose.data.DosageRule
import com.example.drugdose.ui.components.AlertBox
import java.math.RoundingMode

@Composable
fun Result( viewModel: SharedViewModel, onClickAct : () -> Unit ) {

                ResultContent(viewModel, onClickAct)
}
@Composable
fun ResultContent(viewModel: SharedViewModel,onClickAct : () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isAlert by remember{ mutableStateOf(false)}
    val alerts = mutableListOf<String>()
    val profile = uiState.selectedProfile
    val med = uiState.selectedMed
    var dose: Double = 0.0
    var maxDose = 0.0
    val color = MaterialTheme.colorScheme.onBackground
    if (profile == null || med == null) {
        throw Exception("profile or med not selected")
    } else {

        var dosageRule: DosageRule = med.dosageRules[0]
        val age = profile.age
        if (age > med.dosageRules.last().maxAge) {
            alerts.add("MaxAge")
        } else {
            for (rule in med.dosageRules) {
                if (age < rule.minAge) {
                    alerts.add("MinAge")
                    break
                }
                if (age <= rule.maxAge) {
                    dosageRule = rule
                    break
                }

            }

            dose = dosageRule.calculateDose(profile = profile).first
            maxDose =  dosageRule.calculateDose(profile = profile).second
            if (dose == -1.0) alerts.add("MaxWeight")
            if (dose == 0.0) alerts.add("MinWeight")
           if(profile.pregnant && !med.pregnantOk) {
               alerts.add("Pregnant")
           }
            if (dose > maxDose) dose = maxDose
        }

        val commercial = convertToCommercial(dose, med)
        val n = dose / commercial.dose
        if (!alerts.isEmpty()) {
            isAlert = true
        }
        if (isAlert) {
            AlertBox(args = alerts, isMedWarning = true){
                isAlert = false
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            var layout by remember { mutableStateOf<TextLayoutResult?>(null) }
            Text(
                text = "Your Dose Of ${med.name} Is:",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLargeEmphasized,
                onTextLayout = {
                    layout = it
                },
                modifier = Modifier.drawBehind {

                    layout?.let {
                        val thickness = 5f
                        val spacingExtra = 4f
                        val offsetY = 6f

                        for (i in 0 until it.lineCount) {
                            drawPath(
                                path = Path().apply {
                                    moveTo(it.getLineLeft(i), it.getLineBottom(i) - spacingExtra + offsetY)
                                    lineTo(it.getLineRight(i), it.getLineBottom(i) - spacingExtra + offsetY)
                                },
                                color,
                                style = Stroke(
                                    width = thickness,
                                    pathEffect = pathEffect
                                )
                                )
                        }
                    }
                })
            Spacer(modifier = Modifier.size(24.dp))
            Button(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight().dropShadow(RoundedCornerShape(25), Shadow(5.dp)),
                colors = ButtonDefaults.buttonColors().copy(
                    MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                shape = RoundedCornerShape(25),
                onClick =
                    {
                        onClickAct()
                    }
            ) {
                var string =  pluralize(commercial.type , n)+ " of " + commercial.dose + "mg"
                if( commercial.type == "ml") string = "ml"
                Text(
                    text = "${
                        dose.toBigDecimal().setScale(
                            1,
                            RoundingMode.HALF_DOWN
                        )
                    } mg \n or \n " + n.toBigDecimal().setScale(
                        1,
                        RoundingMode.HALF_DOWN
                    ) + " " + string,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.size(40.dp))
            var layoutt by remember { mutableStateOf<TextLayoutResult?>(null) }
            Text(
                text = "Your Maximum Daily Dose  Is:",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLargeEmphasized,
                onTextLayout = {
                    layoutt = it
                },
                modifier = Modifier.drawBehind {

                    layoutt?.let {
                        val thickness = 5f
                        val spacingExtra = 4f
                        val offsetY = 6f

                        for (i in 0 until it.lineCount) {
                            drawPath(
                                path = Path().apply {
                                    moveTo(it.getLineLeft(i), it.getLineBottom(i) - spacingExtra + offsetY)
                                    lineTo(it.getLineRight(i), it.getLineBottom(i) - spacingExtra + offsetY)
                                },
                                color,
                                style = Stroke(
                                    width = thickness,
                                    pathEffect = pathEffect
                                )
                            )
                        }
                    }
                })
            Spacer(modifier = Modifier.size(24.dp))
            Button(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight().dropShadow(RoundedCornerShape(25), Shadow(5.dp)),
                colors = ButtonDefaults.buttonColors().copy(
                    MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                shape = RoundedCornerShape(25),
                onClick =
                    {
                        onClickAct()
                    }
            ) {
              val  nn = maxDose / commercial.dose
                var string =  pluralize(commercial.type,nn) + " of " + commercial.dose + "mg"
                if( commercial.type == "ml") string = "ml"
                Text(
                    text = "${
                        maxDose.toBigDecimal().setScale(
                            1,
                            RoundingMode.HALF_DOWN
                        )
                    } mg \n or \n " + nn.toBigDecimal().setScale(
                        1,
                        RoundingMode.HALF_DOWN
                    ) + " " + string,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
