package com.example.drugdose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.drugdose.R
import com.example.drugdose.data.Medicine
import com.example.drugdose.ui.SharedViewModel

@Composable
fun MedListItem (
    med : Medicine,
    toResult: Boolean,
    viewModel: SharedViewModel,
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
                    style = MaterialTheme.typography.titleMediumEmphasized,
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