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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class List : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            LazyColumn{
                items(10
                ){
                    Text(
                        text = "Element",
                                Modifier.clickable(true, onClick = {
                            val intent = Intent(applicationContext, Form::class.java)
                            startActivity(intent)
                        }
                                )
                    )
                }
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