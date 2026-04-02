package com.example.drugdose.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    //plus button sequence : main -> list -> form (keep stack until here) -> if conflict {alert} else >(handle back button to main) result (return to main {flag activity clear top?})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            appContent()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "What's your dose?")
                Button(onClick = {
                    val intent = Intent(applicationContext, List::class.java)
                    startActivity(intent)})
                {
                    Text(
                        text = "+"
                    )
                }
            }
        }
    }
}
fun appContent(){
//add navigation routes for navbar
}


@Preview
@Composable
fun Preview() {
}
