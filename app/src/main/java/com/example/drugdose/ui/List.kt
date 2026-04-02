package com.example.drugdose.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//is function with handleback()
class List : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val meds = listOf<String>("med1","med2","med3")
        setContent {
            //for each json el put -> list el
            LazyColumn(
                modifier = Modifier.Companion.fillMaxWidth(),
                horizontalAlignment = Alignment.Companion.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                contentPadding = PaddingValues(20.dp)
            ) {
                this.items(meds) { med ->
                    Row(Modifier.Companion.clickable(true, onClick = {
                        val intent = Intent(applicationContext, Form::class.java)
                        intent.putExtra("Medname", med)
                        startActivity(intent)
                    }))
                    { Text(med) }
                }
//
//
//
            }
            }

        }
    }