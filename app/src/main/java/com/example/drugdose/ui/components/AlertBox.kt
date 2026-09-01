package com.example.drugdose.ui.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.drugdose.R


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
                    .wrapContentHeight().padding(24.dp),
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
                       AlertContent(args)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start){
                        Button(
                            modifier = Modifier.wrapContentWidth()
                            .wrapContentHeight(),
                            contentPadding = PaddingValues(0.dp),
                            onClick = {
                                openDialog.value = false
                                onDismiss()
                            },
                            shape = RoundedCornerShape(25)
                        ) {
                            Icon(

                                painterResource(R.drawable.check_40px),
                                "Dismiss",
                                Modifier.size(32.dp)
                            )
                        }
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
    Column {
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        Text(
            modifier = Modifier.drawBehind {
                val strokeWidthPx = 1.dp.toPx()
                val verticalOffset = size.height - 2.sp.toPx()
                drawLine(
                    color = Color.Black,
                    strokeWidth = strokeWidthPx,
                    start = Offset(0f, verticalOffset),
                    end = Offset(size.width, verticalOffset),
                    pathEffect = pathEffect
                )
            },
            text = "Not Reccommended: ",
            style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (args.contains("MaxAge")) {
            Text(
                "Above max age. ",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start
            )
        }
        if (args.contains("MinAge")) {
            Text(
                "Below min age. ",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start
            )
        }
        if (args.contains("MaxWeight")) {
            Text(
                "Above max weight. ",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start
            )
        }
        if (args.contains("MinWeight")) {
            Text(
                "Below min weight. ",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start
            )
        }
        if (args.contains("Pregnant")) {
            Text(
                "Not safe during pregnancy. ",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start
            )
        }
    }

}
@Composable
fun AlertContent(args : List<String>){
    Column {
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        Text(
            modifier = Modifier.drawBehind {
                val strokeWidthPx = 1.dp.toPx()
                val verticalOffset = size.height - 2.sp.toPx()
                drawLine(
                    color = Color.Black,
                    strokeWidth = strokeWidthPx,
                    start = Offset(0f, verticalOffset),
                    end = Offset(size.width, verticalOffset),
                    pathEffect = pathEffect
                )
            },
            text = "Alert ",
            style = MaterialTheme.typography.titleLargeEmphasized,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(8.dp))
        for (arg in args) {
            Text(arg, textAlign = TextAlign.Start)
        }
    }
}