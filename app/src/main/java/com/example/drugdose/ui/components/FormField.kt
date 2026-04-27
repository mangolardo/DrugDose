package com.example.drugdose.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldLabelPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormField(
    state : TextFieldState,
    label : String
){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        state = state,
        labelPosition = TextFieldLabelPosition.Above(Alignment.Start),
        label = {
            Text(
                text=label,
                fontSize = 20.sp
        )},
        placeholder = {Text(text="Type here...")},
        shape = RoundedCornerShape(25),
        contentPadding = PaddingValues(8.dp),
        isError = validateInput(state,label)
    )
}
//fix this method
fun validateInput(state : TextFieldState, label : String) : Boolean{
    return if(state.text == "") false else
        if (label == "Age"){
            (state.text.toString().toIntOrNull() == null || state.text.toString().toInt() <= 0 ||state.text.toString().toInt() > 110)
        }else {
            (state.text.toString().toShortOrNull() == null || state.text.toString().toShort() <= 0|| state.text.toString().toShort() > 210)
        }
}