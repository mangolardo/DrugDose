package com.example.drugdose.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.drugdose.ui.Result

//visualize standard form. Get user input to calculate dose and alerts based on selected medicine
// maybe create method to generate custom form for selected med, based on med profile (ex. med not recc for pregnant subjects, add pregnancy radio button)
// OR calculate alerts based on med BEFORE form
class Form : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = this
        setContent {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = intent.getStringExtra("MedId")!!)

                Button(onClick = {
                    switchAct(activity,Result::class.java)
                })
                { Text(text = "Confirm") }
            }
        }
    }
}
//is activity