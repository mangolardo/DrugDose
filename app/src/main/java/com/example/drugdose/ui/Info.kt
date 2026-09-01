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
               "DrugDose é una applicazione per il calcolo automatico del dosaggio farmacologico che utilizza i parametri fisiologici del paziente e regole terapeutiche provenienti da fonti cliniche verificate(Banca Dati AIFA dei farmaci autorizzati in Italia). \nL'applicazione permette la consultazione del database medico, limitato a 15 prodotti in questa versione sperimentale.\n É possibile creare profili in cui salvare i parametri per ogni eventuale utente che utilizza l'applicazione e consultare la lista dei profili salvati. \nPremendo il bottone principale verrá chiesto all'utente di scegliere un profilo o di crearne uno, successivamente sará visualizzata la lista di farmaci tra cui scegliere il farmaco di cui si vuole sapere il dosaggio. Infine verrá visualizzata la dose in mg e la forma farmaceutica corrispondente disponibile in commercio.\nL'applicazione tiene conto delle limitazioni e range di etá, peso, dose massima e di eventuali controindicazioni per soggetti in gravidanza. ",
               modifier = Modifier.padding(horizontal = 16.dp),
               textAlign = TextAlign.Start,
               style = MaterialTheme.typography.bodyLarge
           )
       }
   }

}