package com.example.drugdose.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.drugdose.R
import com.example.drugdose.ui.components.AlertBox

class EditViewModel : ViewModel(){
    var ageState = TextFieldState()
    var weightState = TextFieldState()

    var heightState = TextFieldState()
    var nameState =  TextFieldState()
    var alerts = mutableListOf<String>()
    var isEdit = mutableStateOf(false)
    fun addAlert(alert : String){
        alerts.add(alert)
    }
}
@Composable
fun ProfileDetail(uiState: UiState,viewModel: SharedViewModel) {
    val editViewModel : EditViewModel = viewModel()
    var isWrong by remember { mutableStateOf(false)}
    var isEdit by editViewModel.isEdit
 //   var isEdit by remember { mutableStateOf(false) }
    val profile = uiState.selectedProfile
    var isPreg by remember {mutableStateOf(profile!!.pregnant)}
    val labels = listOf("Age", "Height", "Weight")
    val ageState : TextFieldState = editViewModel.ageState
    val weightState : TextFieldState = editViewModel.weightState
    val heightState : TextFieldState = editViewModel.heightState
    val nameState : TextFieldState = editViewModel.nameState
    val states = listOf(ageState,heightState,weightState)
    val infos = listOf(profile!!.age.toString(), profile.height.toString(), profile.weight.toString())
    val items = labels zip infos zip states
    val alerts = editViewModel.alerts


    if (isEdit) {
        Scaffold(
            topBar = {
                TopAppBar(
                {InputEdit(nameState,profile.name, false)}, colors = TopAppBarColors(
                        containerColor = Color.Transparent,
                        scrolledContainerColor = Color.Transparent,
                        navigationIconContentColor = Color.Transparent,
                        titleContentColor = MaterialTheme.colorScheme.onBackground,
                        actionIconContentColor =Color.Transparent,
                        subtitleContentColor = Color.Transparent
                    ),
                actions = {
                    IconButton({
                        if  (validateInputEmpty(ageState, "Age"))editViewModel.addAlert("Age must be between 0-110")
                        if ( validateInputEmpty(weightState, "Weight(kg)")) editViewModel.addAlert("Weight must be between 0-230")
                        if (  validateInputEmpty(heightState, "Height(cm)") ) editViewModel.addAlert("Height must be between 0-220")
                        if (  nameState.text.length > 10) editViewModel.addAlert("Profile name can't be longer than 10 characters")
                        val hasError = !alerts.isEmpty()

                        if (hasError) {
                            isWrong = true
                        } else {
                            val newProf = profile.copy(
                                name = if (editViewModel.nameState.text.toString()
                                        .isEmpty()
                                ) profile.name else editViewModel.nameState.text.toString(),
                                age = if (editViewModel.ageState.text.toString()
                                        .isEmpty()
                                ) profile.age else editViewModel.ageState.text.toString().toInt(),
                                height = if (editViewModel.heightState.text.toString()
                                        .isEmpty()) profile.height else editViewModel.heightState.text.toString()
                                    .toInt(),
                                weight = if (editViewModel.weightState.text.toString()
                                        .isEmpty()
                                ) profile.weight else editViewModel.weightState.text.toString()
                                    .toFloat(),
                                pregnant = isPreg
                            )
                            viewModel.updateProfile(newProf)
                            viewModel.selectProfile(newProf)
                            isEdit = false
                        }
                    }
                    ) {
                        Icon(
                            painterResource(id= R.drawable.check_40px), "Save",tint= MaterialTheme.colorScheme.onBackground)
                    }
                    IconButton({
                        editViewModel.ageState = TextFieldState("")
                        editViewModel.weightState = TextFieldState("")
                        editViewModel.heightState = TextFieldState("")
                        editViewModel.nameState = TextFieldState("")
                        isPreg = profile.pregnant
                        isEdit = false
                    }) {
                        Icon(
                            painterResource(id= R.drawable.close_40px), "Cancel",tint= MaterialTheme.colorScheme.onBackground)
                    }
                }
            ) },
            contentWindowInsets = WindowInsets(16.dp,16.dp,16.dp,16.dp)
        )
        { padding ->
            HorizontalDivider(modifier = Modifier.padding(vertical = padding.calculateTopPadding()).fillMaxWidth(),thickness = 1.dp, color = MaterialTheme.colorScheme.onBackground)
            LazyColumn(
                Modifier.padding(padding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(items = items) { item ->
                    val label = item.first.first
                    val info = item.first.second
                    val state = item.second
                    DetailItemEdit(label , info , state, validateInputEmpty(state,label))
                }
                item{
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Column( modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center
                        ){
                            Text("Pregnant?",Modifier.padding(vertical=16.dp), style = MaterialTheme.typography.bodyLargeEmphasized)
                        }

                            RadioButton(
                                modifier = Modifier.size(20.dp),
                                selected = isPreg, enabled = true,
                                onClick = { isPreg = !isPreg}
                            )

                    }
                }


            }
        }

    } else {
        Scaffold(
            topBar = {TopAppBar(
                {Text(profile.name, textAlign = TextAlign.Start)},
                colors = TopAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent,
                    navigationIconContentColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    actionIconContentColor =Color.Transparent,
                    subtitleContentColor = Color.Transparent
                ),

                actions = {
                    IconButton({isEdit = true}) {
                        Icon(
                            painterResource(id= R.drawable.edit_40px), "Edit",tint= MaterialTheme.colorScheme.onBackground, modifier = Modifier.size(35.dp))
                    }
                }
                ) },
            contentWindowInsets = WindowInsets(16.dp,16.dp,16.dp,16.dp)
            )
         { padding ->
            HorizontalDivider(modifier = Modifier.padding(vertical = padding.calculateTopPadding()).fillMaxWidth(),thickness = 1.dp, color = MaterialTheme.colorScheme.onBackground)
           Spacer(Modifier.height(16.dp))
            LazyColumn(
                Modifier.padding(padding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(items = items) { item ->
                    DetailItem(item.first.first, item.first.second)
                }
                item{
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Column( modifier = Modifier.weight(4f),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center
                        ){ Text("Pregnant?",Modifier.padding(vertical=16.dp).fillMaxWidth(), style = MaterialTheme.typography.bodyLargeEmphasized)

                        }
                            RadioButton(
                                modifier = Modifier.size(20.dp),
                                selected = profile.pregnant,
                                enabled = false,
                                onClick = { })
                        }
                    }
                }
        }
    }
    if(isWrong) {
        AlertBox(
            args = alerts,
            isMedWarning = false,
            onDismiss = {
                isWrong = false
                editViewModel.alerts = mutableListOf<String>()
            }
        )
    }
}

@Composable
fun DetailItem(label:String, info:String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ){
        Column( modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ){
            Text(label,Modifier.padding(vertical=16.dp), style = MaterialTheme.typography.bodyLargeEmphasized)}
        Column( modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ){
            Text(info,Modifier.padding(vertical=16.dp), style = MaterialTheme.typography.bodyLarge)}
    }
}

@Composable
fun DetailItemEdit(label:String, info:String, state : TextFieldState, isError: Boolean){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ){
        Column( modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ){
            Text(label,Modifier.padding(vertical=16.dp), style = MaterialTheme.typography.bodyLargeEmphasized)
        }

        Column( modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End
        ){
            InputEdit(state, info, isError)}
    }
}

@Composable
fun InputEdit(state : TextFieldState, info:String, isError : Boolean ){
    TextField(state,
        modifier= Modifier
            .width(130.dp).height(50.dp)
        ,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
        placeholder = {Text(info, style = MaterialTheme.typography.bodyLarge)}, isError = isError,
        lineLimits = TextFieldLineLimits.SingleLine,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent ,
            focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
        )

        )
}