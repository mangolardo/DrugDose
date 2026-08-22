package com.example.drugdose.ui

import android.app.Activity
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
        Text(text = "What's your dose?", style = MaterialTheme.typography.titleLargeEmphasized)
        Spacer(
            modifier = Modifier
                .height(15.dp)
        )
        FloatingActionButton(onClick = plusAction) {
            Icon(
                painterResource(id = R.drawable.add_40px),
                contentDescription = "Medicine List"
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
        Spacer(Modifier.size(48.dp))
    }
   }

