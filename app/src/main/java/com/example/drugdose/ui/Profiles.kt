package com.example.drugdose.ui

import androidx.compose.runtime.Composable

//is function with handleback()
@Composable
fun Profiles(
    toMedList: Boolean,
    clickAct: () -> Unit,
    onNew: () -> Unit,
    isEdit: Boolean,
    uiState: UiState
){
//lazycolumn with db request for allprofiles
    //button NEW PROFILE {on new}
    //on click listener if toMedlist = true ontomedlist action
    // else open profile detail, set selectedprofile


}