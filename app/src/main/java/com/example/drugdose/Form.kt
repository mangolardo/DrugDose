package com.example.drugdose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.*
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Form : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Text(
                text= "ciao"
            )
        }
    }
}