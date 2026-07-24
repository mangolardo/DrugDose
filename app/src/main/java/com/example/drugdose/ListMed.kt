package com.example.drugdose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.drugdose.ui.MedListContent
import com.example.drugdose.ui.theming.AppTheme

@Composable
fun ListMed(){
//use query to room db for medlist
            val medLazyListState = rememberLazyListState()
                    MedListContent(
                        json = this.assets.open("medsJson.json"),
                        action = { medId -> switchToForm(this, medId) },
                        state = medLazyListState
                    )
                }