package com.example.drugdose.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.drugdose.R
import com.example.drugdose.data.Profile

@Composable
fun Profiles(
    clickAct: () -> Unit,
    onNew: () -> Unit,
    uiState: UiState,
    viewModel: SharedViewModel
){
    val state = rememberLazyListState()
    val list by uiState.profiles.collectAsStateWithLifecycle(initialValue = emptyList())
Scaffold(topBar = {
    TopAppBar({
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        Text("Profiles", style = MaterialTheme.typography.titleLargeEmphasized, modifier = Modifier.drawBehind {
        val strokeWidthPx = 1.dp.toPx()
        val verticalOffset = size.height - 2.sp.toPx()
        drawLine(
            color = Color.Black,
            strokeWidth = strokeWidthPx,
            start = Offset(0f, verticalOffset),
            end = Offset(size.width, verticalOffset),
            pathEffect = pathEffect
        )
    },)}, colors = TopAppBarColors(
        containerColor = Color.Transparent,
        scrolledContainerColor = Color.Transparent,
        navigationIconContentColor = Color.Transparent,
        titleContentColor = MaterialTheme.colorScheme.scrim,
        actionIconContentColor =Color.Transparent,
        subtitleContentColor = Color.Transparent
    ))
},
    bottomBar = {
        BottomAppBar(

            actions = {
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick =  onNew
                ) {
                    Icon(painterResource(id = R.drawable.add_24px), contentDescription = "New Profile")
                }
            }, containerColor = Color.Transparent
        )
    }
)
{ paddingValues ->

    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        horizontalAlignment = Alignment.Start
    ) {
        items(items = list) { profile ->
            ProfileItem(profile = profile, viewModel) {
                clickAct()
            }
        }
    }


}
}
//BottomAppBar(
//floatingActionButton = {
//    FloatingActionButton(onClick = onNew) {
//        Icon(
//            painterResource(id = R.drawable.add_40px),
//            contentDescription = "Start"
//        )
//    }}
//) {  }

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
            .clickable() {
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
        HorizontalDivider(modifier = Modifier.fillMaxWidth(),thickness = 1.dp, color = Color.Black)

    }
}