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
import androidx.compose.material3.HorizontalDivider
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
import com.example.drugdose.data.Profile
import com.example.drugdose.ui.SharedViewModel

@Composable
fun ProfileItem(
    profile : Profile,
    viewModel : SharedViewModel,
    clickAct: () -> Unit

){
    Card(
        colors = CardDefaults.cardColors().copy(containerColor = MaterialTheme.colorScheme.background),
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                viewModel.resetUi()
                viewModel.selectProfile(profile)
                clickAct()
            }
    )
    {
        Row( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier
                .padding(16.dp)
                .weight(1f)
            ) {
                Text(
                    text = profile.name,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ){
                Icon(
                    painter = painterResource(id = R.drawable.person_40px),
                    contentDescription = "Profile ${profile.name}",

                    )

            }
        }
        HorizontalDivider(modifier = Modifier.fillMaxWidth(),thickness = 1.dp, color = MaterialTheme.colorScheme.onBackground)

    }
}