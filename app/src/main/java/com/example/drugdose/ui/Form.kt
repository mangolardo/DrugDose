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
import androidx.lifecycle.ViewModel
import com.example.drugdose.ui.components.AlertBox

class FormViewModel : ViewModel(){
    val ageState = TextFieldState()
    val weightState = TextFieldState()

    val heightState = TextFieldState()
    val nameState =  TextFieldState()
}

@Composable
fun Form(toMedList: Boolean,
         clickAct: () -> Unit,
         uiState: UiState,
         viewModel: SharedViewModel
)
 {

   val formView: FormViewModel = viewModel()
     var isWrong by remember { mutableStateOf(false)}

     if(isWrong) {
         AlertBox(
             args = listOf("Fields are wrong"),
             isMedWarning = false,
             onDismiss = { isWrong = false }
         )
     }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        val ageState : TextFieldState = formView.ageState
        val weightState : TextFieldState = formView.weightState
        val heightState : TextFieldState = formView.heightState
        val nameState : TextFieldState = formView.nameState
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(false) }

        Text(text = "Form ")
        //val nameState = uiState.nameState
         FormField(
          state = nameState,
            label = "Name of profile",
            isError =  nameState.text.isEmpty()
        )

        //val weightState = uiState.weightState
        FormField(
            state = weightState,
            label = "Weight(kg)",
            isError = validateInput(weightState,"Weight(kg)")
        )

        //val heightState = uiState.heightState
        FormField(
            state = heightState,
            label = "Height(cm)",
            isError = validateInput(heightState,"Height(cm)")
        )
       // val ageState = uiState.ageState
      FormField(
            state = ageState,
            label = "Age",
           isError = validateInput(ageState,"Age")
        )
        Column(modifier = Modifier.selectableGroup()) {
            Row() {
                Text(text="Pregnant")
                RadioButton(
                    selected = selectedOption ,
                    onClick = {onOptionSelected(!selectedOption)}
                )
            }
        }
        val weight = weightState.text.toString()
        val name = nameState.text.toString()
        val height = heightState.text.toString()
        val age = ageState.text.toString()

        Button(
            //if ontoMedList save profile fields to viewmodel AND save profile to db
            //else save profile to db and go back profiles
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(25) ,
            onClick = {
                val hasError = validateInput(ageState, "Age") ||
                        validateInput(weightState, "Weight(kg)") ||
                        validateInput(heightState, "Height(cm)") ||
                        nameState.text.isEmpty()

                if (!hasError) {
                    viewModel.resetUi()
                    val profile: Profile = Profile(
                        name = name,
                        age = age.toInt(),
                        weight = weight.toShort(),
                        height = height.toShort(),
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
        { Text(text = "Confirm")
    }


     }

}