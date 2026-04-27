package com.example.drugdose.ui

import android.content.Intent
import androidx.activity.ComponentActivity
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.drugdose.MainActivity
import com.example.drugdose.data.Dosage
import com.example.drugdose.ui.components.AlertBox
import kotlinx.serialization.json.Json
import java.math.RoundingMode

@Composable
fun ResultContent(args: List<String>, intent : Intent, activity : ComponentActivity) {
    val dose = args.last().toFloat()
    val name = intent.getStringExtra("name")
    val commercial =  Json.Default.decodeFromString<Dosage>(intent.getStringExtra("commercial")!!)
    val n = dose.toDouble()/commercial.dose
    if (args.dropLast(1).isNotEmpty()) {
        AlertBox(args = args.dropLast(1))
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text= "Your Daily Dose Of $name Is:")
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(25),
            onClick =
            {

                val intent = Intent(activity, MainActivity::class.java)

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                activity.startActivity(intent)
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