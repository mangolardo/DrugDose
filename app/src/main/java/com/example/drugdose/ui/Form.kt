package com.example.drugdose.ui

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.drugdose.data.Profile
import com.example.drugdose.ui.components.FormField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.drugdose.ui.components.AlertBox

class FormViewModel : ViewModel(){
    val ageState = TextFieldState()
    val weightState = TextFieldState()

    val heightState = TextFieldState()
    val nameState =  TextFieldState()
    val isPregnant = mutableStateOf(false)
    var alerts = mutableListOf<String>()
    fun addAlert(alert : String){
        alerts.add(alert)
    }
    fun clearAlerts(){
        alerts =  mutableListOf<String>()
    }
}

@Composable
fun Form(toMedList: Boolean,
         clickAct: () -> Unit,
         viewModel: SharedViewModel
) {

    val formView: FormViewModel = viewModel()
    var isWrong by remember { mutableStateOf(false) }
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    val color = MaterialTheme.colorScheme.onBackground
    Scaffold(
        topBar ={TopAppBar(
            {
                Text(
                    text = "Form ",
                    style = MaterialTheme.typography.titleLargeEmphasized,
                    modifier = Modifier.drawBehind {
                        val strokeWidthPx = 1.dp.toPx()
                        val verticalOffset = size.height - 2.sp.toPx()
                        drawLine(
                            color = color,
                            strokeWidth = strokeWidthPx,
                            start = Offset(0f, verticalOffset),
                            end = Offset(size.width, verticalOffset),
                            pathEffect = pathEffect
                        )
                    })
            }

    )}) {
        paddingValues ->
        if (isWrong) {
            AlertBox(
                args = formView.alerts,
                isMedWarning = false,
                onDismiss = {
                    isWrong = false
                    formView.clearAlerts()
                }
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues).verticalScroll(rememberScrollState())
        ) {
            val ageState: TextFieldState = formView.ageState
            val weightState: TextFieldState = formView.weightState
            val heightState: TextFieldState = formView.heightState
            val nameState: TextFieldState = formView.nameState
            val (selectedOption, onOptionSelected) = formView.isPregnant

            FormField(
                state = nameState,
                label = "Name of profile",
                isError = nameState.text.length > 10
            )
            FormField(
                state = weightState,
                label = "Weight(kg)",
                isError = validateInputEmpty(weightState, "Weight(kg)")
            )
            FormField(
                state = heightState,
                label = "Height(cm)",
                isError = validateInputEmpty(heightState, "Height(cm)")
            )
            FormField(
                state = ageState,
                label = "Age",
                isError = validateInputEmpty(ageState, "Age")
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Pregnant", style = MaterialTheme.typography.bodyLargeEmphasized)
                }
                RadioButton(
                    selected = selectedOption,
                    onClick = { onOptionSelected(!selectedOption) }
                )
            }
            val weight = weightState.text.toString()
            val name = nameState.text.toString()
            val height = heightState.text.toString()
            val age = ageState.text.toString()

            Button(

                //if ontoMedList save profile fields to viewmodel AND save profile to db
                //else save profile to db and go back profiles
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 16.dp),
                shape = RoundedCornerShape(25),
                onClick = {

                    if (nameState.text.isEmpty() || ageState.text.isEmpty() || heightState.text.isEmpty() || weightState.text.isEmpty()) formView.addAlert(
                        "Fields can't be empty"
                    )
                    if (nameState.text.length > 10) formView.addAlert("Profile name can't be longer than 10 characters")
                    if (validateInputEmpty(
                            ageState,
                            "Age"
                        )
                    ) formView.addAlert("Age must be between 0-100")
                    if (validateInputEmpty(
                            weightState,
                            "Weight(kg)"
                        )
                    ) formView.addAlert("Weight must be between 3-230")
                    if (validateInputEmpty(
                            heightState,
                            "Height(cm)"
                        )
                    ) formView.addAlert("Height must be between 60-220")
                    val hasError = !formView.alerts.isEmpty()

                    if (!hasError) {
                        viewModel.resetUi()
                        val profile: Profile = Profile(
                            name = name,
                            age = age.toInt(),
                            weight = weight.toFloat(),
                            height = height.toInt(),
                            pregnant = selectedOption
                        )
                        viewModel.addProfile(profile)
                        if (toMedList) {
                            viewModel.selectProfile(profile)
                        }
                        clickAct()
                    } else {
                        isWrong = true
                    }
                }
            )
            {
                Text(text = "Confirm")
            }


        }

    }
}