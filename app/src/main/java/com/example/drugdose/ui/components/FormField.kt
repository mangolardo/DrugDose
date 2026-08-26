package com.example.drugdose.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
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
    label : String,
    isError : Boolean = false
){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        state = state,
        labelPosition = TextFieldLabelPosition.Above(Alignment.Start),
        label = {
            Text(
                text=label,
               style = MaterialTheme.typography.bodyLargeEmphasized
        )},
        placeholder = {Text(text="Type here...")},
        shape = RoundedCornerShape(25),
        contentPadding = PaddingValues(8.dp),
        isError = isError
    )
}
//fix this method
