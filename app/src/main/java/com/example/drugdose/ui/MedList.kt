package com.example.drugdose.ui

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.view.Display
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.drugdose.R
import com.example.drugdose.data.Medicine
import com.example.drugdose.ui.components.MedListItem
import com.example.drugdose.ui.components.StatusBarProtection

@SuppressLint("SuspiciousIndentation")
@Composable
fun ListMed(
    toResult: Boolean,
    ontoResult: () -> Unit,
    uiState: UiState,
    viewModel: SharedViewModel

) {
    Scaffold() { padding ->
        val state = rememberLazyListState()
        val list = uiState.meds
        val pad = if( padding.calculateLeftPadding(LayoutDirection.Ltr) > 0.dp) {padding.calculateLeftPadding(LayoutDirection.Ltr)} else {padding.calculateRightPadding(LayoutDirection.Ltr)}
        LazyColumn(
            state = state,
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = with(LocalDensity.current) {
                (WindowInsets.statusBars.getTop(
                    this
                ) * 1.2f).toDp()
            }, horizontal = pad )
        ) {
            items(items = list) { med ->
                MedListItem(med = med, toResult = toResult, viewModel) { ontoResult() }
            }
        }
        StatusBarProtection()
    }
}

