package com.example.drugdose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.drugdose.data.Profile
import com.example.drugdose.ui.components.FormField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun Form(toMedList: Boolean, clickAct: () -> Unit, uiState: UiState, viewModel: SharedViewModel) {


    val med = uiState.selectedMed
        // createAlert(medId) //alert per controindicazioni

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        val ageState : TextFieldState = TextFieldState()
        val weightState : TextFieldState = TextFieldState()
        val heightState : TextFieldState = TextFieldState()
        val nameState : TextFieldState = TextFieldState()
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(false) }

        Text(text = "Form for " + med!!.name)
        //val nameState = uiState.nameState
        FormField(
            state = nameState,
            label = "Name of profile"
        )

        //val weightState = uiState.weightState
        FormField(
            state = weightState,
            label = "Weight(kg)"
        )

        //val heightState = uiState.heightState
        FormField(
            state = heightState,
            label = "Height(cm)"
        )
       // val ageState = uiState.ageState
        FormField(
            state = ageState,
            label = "Age"
        )
        Column(modifier = Modifier.selectableGroup()) {
            Row() {
                Text(text="Pregnant")
                RadioButton(
                    selected = selectedOption ,
                    onClick = {onOptionSelected(true)}
                )
            }
        }


        val name = nameState.text.toString()
        val weight = weightState.text.toString().toShort()
        val height = heightState.text.toString().toShort()
        val age = ageState.text.toString().toInt()



        Button(
            //if ontoMedList save profile fields to viewmodel AND save profile to db
            //else save profile to db and go back profiles
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(25) ,
            onClick = {
                viewModel.resetUi()
                val profile : Profile = Profile(
                    name = name,
                    age = age,
                    weight = weight,
                    height = height,
                    pregnant = selectedOption
                )
                viewModel.addProfile(profile)
                if(toMedList){
                viewModel.selectProfile(profile)
                }
                clickAct()
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