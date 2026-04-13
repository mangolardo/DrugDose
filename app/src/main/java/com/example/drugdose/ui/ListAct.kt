package com.example.drugdose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.drugdose.data.Medicine
import com.example.drugdose.ui.components.MedList
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.getValue

//is activity
class ListAct : ComponentActivity() {
    lateinit var drugModel : DrugDoseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // drugModel() = ViewModelProvider(this)[DrugDoseViewModel::class.java]

        val file =
            (this.assets.open("medsJson.json").bufferedReader().use { it.readText()})

       val list = Json.decodeFromString<List<Medicine>>(file)

          drugModel = DrugDoseViewModel(list)
//             val list2 = listOf(Medicine("11", "med1"), Medicine("22", "med2"), Medicine("33", "med3"))


        setContent {
            Text(text=file)

            //val uiState by viewModel.uistate.collectAsStateWithLifecycle()

            val medLazyListState = rememberLazyListState()
            MedList(
                meds = drugModel._meds ,
                action = { medId -> switchToForm(this, medId) },
                state = medLazyListState
            )

        }

    }
}

