package com.example.drugdose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.drugdose.data.Medicine
import com.example.drugdose.ui.FormContent
import com.example.drugdose.ui.theming.AppTheme
import kotlinx.serialization.json.Json
import kotlin.getValue

//visualize standard form. Get user input to calculate dose and alerts based on selected medicine
// maybe create method to generate custom form for selected med, based on med profile (ex. med not recc for pregnant subjects, add pregnancy radio button)
// OR calculate alerts based on med BEFORE form
class Form : ComponentActivity() {
    class formViewModel : ViewModel(){
        val ageState = TextFieldState()
        val weightState = TextFieldState()
        val heightState =TextFieldState()
    }
    private val viewModel: formViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val activity = this
        val medId = intent.getStringExtra("MedId")!!
        val med = Json.decodeFromString<Medicine>(medId)
       // createAlert(medId) //alert per controindicazioni
        setContent {
            AppTheme {
                Surface(tonalElevation = 5.dp) {
                    FormContent(med, this, viewModel)
                }
            }
        }
    }
}