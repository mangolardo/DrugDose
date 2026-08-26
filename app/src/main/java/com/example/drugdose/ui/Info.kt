package com.example.drugdose.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.drugdose.R

//is function with handleback()
@Composable
fun Info(){
   Scaffold(topBar = {

       TopAppBar({
           val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
           Text("How it works", modifier = Modifier.drawBehind {
           val strokeWidthPx = 1.dp.toPx()
           val verticalOffset = size.height - 2.sp.toPx()
           drawLine(
               color = Color.Black,
               strokeWidth = strokeWidthPx,
               start = Offset(0f, verticalOffset),
               end = Offset(size.width, verticalOffset),
               pathEffect = pathEffect
           )
       }, style = MaterialTheme.typography.titleLargeEmphasized)}) }
   )
   {

       paddingValues ->
       Column( modifier = Modifier.padding(paddingValues)) {

           Text(
               "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
               modifier = Modifier.padding(horizontal = 16.dp), style = MaterialTheme.typography.bodyLarge
           )
       }
   }

}