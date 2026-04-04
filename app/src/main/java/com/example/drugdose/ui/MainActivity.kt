package com.example.drugdose.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.io.File
import kotlin.getValue

class MainActivity : ComponentActivity() {

    //plus button sequence : main -> list -> form (keep stack until here) -> if conflict {alert} else >(handle back button to main) result (return to main {flag activity clear top?})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent(this, ListAct::class.java)

        }
    }
}
@Composable
fun AppContent(activity : Activity, nextClass : Class<*> ){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "What's your dose?")
        Button(onClick = {
            switchAct(activity,nextClass)})
        {
            Text(
                text = "+"
            )
        }
    }
//add navigation routes for navbar
}

