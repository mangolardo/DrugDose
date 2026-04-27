package com.example.drugdose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import com.example.drugdose.data.Dosage
import com.example.drugdose.ui.ResultContent
import com.example.drugdose.ui.components.AlertBox
import com.example.drugdose.ui.theming.AppTheme
import kotlinx.serialization.json.Json
import java.math.RoundingMode

class Result : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this
        val callback = this.onBackPressedDispatcher.addCallback(this){

            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            startActivity(intent)
        }
        val result = intent.getStringExtra("dose")!!
        //parse the result to create alert for min/max age/weight
        val args = result.split(",")


//        if(!intent.getBooleanExtra("pregnantOk",true)){
//            txt += "Not safe during pregnancy. "
//        }

        setContent {
            AppTheme {
                Surface(tonalElevation = 5.dp) {
                    ResultContent(args, intent, this)
                }

            }
        }
        }

        }