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
import androidx.compose.ui.text.style.TextAlign
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
           val color = MaterialTheme.colorScheme.onBackground
           val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
           Text("How it works", modifier = Modifier.drawBehind {
           val strokeWidthPx = 1.dp.toPx()
           val verticalOffset = size.height - 2.sp.toPx()
           drawLine(
               color = color,
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
               "DrugDose is an application for automatic pharmacological dosage calculation that uses patient physiological parameters and therapeutic rules from verified clinical sources (AIFA Database of authorized drugs in Italy).\n" +
                       "\n" +
                       "The application allows manual consultation of the medical database, limited to 12 products in this experimental version. A search feature might be added in future releases\n" +
                       "\n" +
                       "Users can create profiles to save parameters for each person that utilizes the application and view the list of saved profiles.\n" +
                       "\n" +
                       "Pressing the main button prompts the user to select or create a profile, after which a list of medications is displayed to choose the drug for dosage calculation. Finally, the calculated dose in mg and the corresponding commercially available pharmaceutical form will be displayed.\n" +
                       "\n" +
                       "The application accounts for age and weight limitations and ranges, maximum dosage, and potential contraindications for pregnant patients.",
               modifier = Modifier.padding(horizontal = 16.dp),
               textAlign = TextAlign.Start,
               style = MaterialTheme.typography.bodyLarge
           )
       }
   }

}