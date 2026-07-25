package com.example.drugdose.ui.components

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable

@Composable
fun NavBar(
    onProfiles: () -> Unit,
    onInfo: () -> Unit,
    onMeds: () -> Unit
){
    Button(onClick = onMeds){}
    Button(onClick = onInfo){}
    Button(onClick = onProfiles){}
}