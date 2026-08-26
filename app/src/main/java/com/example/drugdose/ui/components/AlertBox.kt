package com.example.drugdose.ui.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertBox(
    args: List<String>,
    isMedWarning : Boolean,
    onDismiss: () -> Unit = {}
) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        BasicAlertDialog(
            onDismissRequest = {
                openDialog.value = false
                onDismiss()
            },
            modifier = Modifier,
            properties = DialogProperties(),
        ) {
            Surface(
                modifier = Modifier
                      .wrapContentWidth()
                      .wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation,
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (isMedWarning) {
                        MedWarningContent(args)
                    } else {
                        for(arg in args){
                            Text(arg)
                        }

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            openDialog.value = false
                            onDismiss()
                        },
                        shape = RoundedCornerShape(25)
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }
}
@Composable
fun MedWarningContent(
    args: List<String>
){
    Text("This medication is not recommended to you for the following reasons : ")
    if (args.contains("MaxAge")) {
        Text("Above max age ")
    }
    if (args.contains("MinAge")) {
        Text("Below min age. ")
    }
    if (args.contains("MaxWeight")) {
        Text("Above max weight. ")
    }
    if (args.contains("MinWeight")) {
        Text("Below min weight. ")
    }

}