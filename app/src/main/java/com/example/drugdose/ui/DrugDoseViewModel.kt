package com.example.drugdose.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.drugdose.data.Medicine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import com.example.drugdose.data.*
import java.io.File


class DrugDoseViewModel(meds : List<Medicine>) : ViewModel(){
    val uistate = MutableStateFlow(UiState())
    init {
        initList(meds)
    }

    private fun initList(meds : List<Medicine>) {
        uistate.value = UiState(
            meds = meds
        )
    }
}
data class UiState(
    val meds : List<Medicine> = emptyList()
)

//ui state for selected med on list,for navigation, form values to remember