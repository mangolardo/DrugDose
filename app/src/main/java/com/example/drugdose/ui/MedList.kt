package com.example.drugdose.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.drugdose.R
import com.example.drugdose.data.Medicine
import kotlin.collections.emptyList


@SuppressLint("SuspiciousIndentation")
@Composable
fun ListMed(
    toResult: Boolean,
    ontoResult: () -> Unit,
    uiState: UiState,
    viewModel: SharedViewModel

) {
        val state = rememberLazyListState()
        val list = uiState.meds
        LazyColumn(
            state = state,
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = with(LocalDensity.current) { (WindowInsets.statusBars.getTop(this) * 1.2f).toDp() })
        ) {
            items(items = list) { med ->
                MedListItem(med = med, toResult = toResult, viewModel, uiState) { ontoResult() }
            }
        }
        StatusBarProtection()

}
@Composable
private fun StatusBarProtection(
    color: Color = MaterialTheme.colorScheme.surfaceContainer,
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(
                with(LocalDensity.current) {
                    (WindowInsets.statusBars.getTop(this) * 1.2f).toDp()
                }
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        color.copy(alpha = 1.8f),
                        color.copy(alpha = 1.5f),
                        Color.Transparent
                    )
                )
            )
    )
}
@Composable
fun MedListItem (
    med : Medicine,
    toResult: Boolean,
    viewModel: SharedViewModel,
    uiState: UiState,
    action : () -> Unit

)
{

 Card(
     colors = CardDefaults.cardColors().copy(containerColor = MaterialTheme.colorScheme.background),
     shape = RectangleShape,
     modifier = Modifier
     .fillMaxWidth()
     .clickable(enabled = toResult) {
         viewModel.resetMed()
         viewModel.selectMed(med)
         action() }
 )
 {
     Row( modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
         horizontalArrangement = Arrangement.SpaceAround,
         verticalAlignment = Alignment.CenterVertically
     ) {
         Column(modifier = Modifier
             .weight(3f),
             horizontalAlignment = Alignment.Start
         ) {
             Text(
                 text = med.name,
                 style = MaterialTheme.typography.titleLargeEmphasized,
                 textAlign = TextAlign.Start
             )
             Text(
                 text = med.desc,
                 style = MaterialTheme.typography.bodyMedium ,
             )
         }
         Box(modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
             contentAlignment = Alignment.CenterEnd
         ){
             Icon(
                 painter = painterResource(id = R.drawable.pill_24px),
                 contentDescription = null,
             )

         }
     }

 }
}