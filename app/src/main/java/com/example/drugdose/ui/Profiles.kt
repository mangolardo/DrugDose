package com.example.drugdose.ui

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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.drugdose.R
import com.example.drugdose.data.Profile

//is function with handleback()
@Composable
fun Profiles(
    clickAct: () -> Unit,
    onNew: () -> Unit,
    uiState: UiState,
    viewModel: SharedViewModel
){
    val state = rememberLazyListState()
    val list by uiState.profiles.collectAsStateWithLifecycle(initialValue = emptyList())
    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(2.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(items = list) { profile ->
            viewModel.resetUi()
            viewModel.selectProfile(profile)
           ProfileItem(profile = profile) {
               clickAct()
           }
        }
    }

//lazycolumn with db request for allprofiles
    //button NEW PROFILE {on new}
    //on click listener if toMedlist = true ontomedlist action
    // else open profile detail, set selectedprofile


}
@Composable
fun ProfileItem(
    profile : Profile,
    clickAct: () -> Unit
    ){
    Card( shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .clickable() { clickAct() }
    )
    {
        Row( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier
                .padding(10.dp)
            ) {
                Text(
                    text = profile.name,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Start
                )
            }
            Box(
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)

                )

            }
        }

    }
}