package com.example.drugdose.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.drugdose.ui.theming.AppTheme

@Composable
fun Result(){
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
