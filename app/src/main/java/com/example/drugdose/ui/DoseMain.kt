package com.example.drugdose.ui

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.drugdose.switchAct

@Composable
fun DoseMain(activity : Activity, nextClass : Class<*>){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "What's your dose?")
        Spacer(
            modifier = Modifier
                .height(15.dp)
        )
        Button(
            shape = CircleShape,
            onClick = {
                switchAct(activity, nextClass)
            })
        {
            Text(
                text = "+"
            )
        }
    }
   }

