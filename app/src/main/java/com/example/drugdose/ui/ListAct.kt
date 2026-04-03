package com.example.drugdose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.drugdose.data.Medicine
import com.example.drugdose.ui.components.MedList
import kotlin.getValue

//is activity
class ListAct : ComponentActivity() {
    private val viewModel: DrugDoseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
                 //listOf(Medicine(1, "med1"), Medicine(2, "med2"), Medicine(3, "med3"))
        setContent {
            val uiState by viewModel.uistate.collectAsStateWithLifecycle()
            val meds = uiState.meds

            val medLazyListState = rememberLazyListState()
            MedList(
                meds = meds,
                action = { medId -> switchToForm(this, medId) },
                state = medLazyListState
            )

        }

    }
}
