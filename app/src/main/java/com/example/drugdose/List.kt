package com.example.drugdose

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp


class List : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val meds = listOf<String>("med1","med2","med3")
        setContent {
            //for each json el put -> list el
            LazyColumn(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, contentPadding = PaddingValues(20.dp)){
                this.items(meds){
                    med -> Row(Modifier.clickable(true, onClick = {
                          val intent = Intent(applicationContext, Form::class.java)
                    intent.putExtra("Medname", med)
                           startActivity(intent)
                    }))
               {Text(med)}}
//
//
//
                }
            }

        }
    }

/*@Composable
//creates a list of n elements for each entry in data file
//argument will be json array ig?
fun MedList(n : Int){
    //every element clickable, expandable?
    LazyColumn(
        Modifier.clickable(true, onClick = {
            val intent = Intent(applicationContext, Form::class.java)
            startActivity(intent)
        })
    ){
        items(n){
            Text(
            text = "Element"
        )}
}

}*/