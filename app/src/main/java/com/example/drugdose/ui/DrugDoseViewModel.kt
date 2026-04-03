package com.example.drugdose.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.drugdose.data.Medicine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import com.example.drugdose.data.*
import java.io.File

class DrugDoseViewModel : ViewModel(){
    val uistate = MutableStateFlow(UiState())
    init{
        initMeds()
    }
    fun initMeds(){
        val file = File("./src/main/java/com/example/drugdose/data/medsJson.json").toString()
        val allMeds = Json.decodeFromString<List<Medicine>>(file)

        uistate.value = UiState(
            meds = allMeds
        )
    }

}
data class UiState(
    val meds : List<Medicine> = emptyList()
)

//ui state for selected med on list,for navigation, form values to remember