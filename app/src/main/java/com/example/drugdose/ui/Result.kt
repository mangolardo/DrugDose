package com.example.drugdose.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.drugdose.data.Dosage
import com.example.drugdose.ui.components.AlertBox
import kotlinx.serialization.decodeFromString
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



        val dose = args.last()
        val name = intent.getStringExtra("name")
        val commercial =  Json.decodeFromString<Dosage>(intent.getStringExtra("commercial")!!)
        val n = dose.toDouble()/commercial.dose

//        if(!intent.getBooleanExtra("pregnantOk",true)){
//            txt += "Not safe during pregnancy. "
//        }

        setContent {
           if (args.dropLast(1).isNotEmpty()) { AlertBox(args = args.dropLast(1)) }
           Button(
                {

                   val intent = Intent(this, MainActivity::class.java)

                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                  startActivity(intent)
                }
            ){
            Text(text = "$name$dose mg. Equivalent:" + n.toBigDecimal().setScale(1, RoundingMode.HALF_DOWN) + " " + commercial.type + " of " + commercial.dose )
            }
            }
        }

        }
//is activity