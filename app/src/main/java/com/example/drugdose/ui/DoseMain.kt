package com.example.drugdose.ui

import android.app.Activity
import android.provider.CalendarContract
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.drugdose.R
import com.example.drugdose.ui.components.NavBar

@Composable
fun DoseMain(
    plusAction: () -> Unit,
    onProfiles: () -> Unit,
    onInfo: () -> Unit,
    onMeds: () -> Unit

){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
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
            text = "What's your dose?",
            style = MaterialTheme.typography.titleLargeEmphasized
        )
//        Text(text = "What's your dose?", style = MaterialTheme.typography.titleLargeEmphasized)
        Spacer(
            modifier = Modifier
                .height(15.dp)
        )
        FloatingActionButton(onClick = plusAction) {
            Icon(
                painterResource(id = R.drawable.add_40px),
                contentDescription = "Start"
            )
        }

    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
    ) {
        NavBar(
            onProfiles = onProfiles,
            onInfo= onInfo,
            onMeds= onMeds
        )
        Spacer(Modifier.size(32.dp))
    }
   }

