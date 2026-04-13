package com.example.drugdose.ui

import android.content.Intent
import android.os.Bundle
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

class Result : AppCompatActivity() {
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
        var txt = ""

        if(args.contains("MaxAge")){ txt += "Etá max superata. "}
        else if(args.contains("MinAge")){txt += "Etá inferiore al minimo. "}
        else if(args.contains("MaxWeight")){txt += "Peso max superato. "}
        else if(args.contains("MinWeight")){txt += "Peso inferiore al minimo. "}
        txt += args.last() + "mg"



        setContent {
           Button(
                {

                   val intent = Intent(this, MainActivity::class.java)

                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                  startActivity(intent)
                }
            ){
            Text(text = txt)
            }
            }
        }

        }
//is activity