package com.example.drugdose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.drugdose.R
import com.example.drugdose.data.Medicine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.InputStream

@Composable
fun MedList(
    json : InputStream,
    action : (String) -> Unit ={},
    state : LazyListState
){
    var list by remember { mutableStateOf<List<Medicine>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true)}
    LaunchedEffect(Unit) {
    val file = withContext(Dispatchers.IO){
       json.bufferedReader().use { it.readText()}

        }
        isLoading = false
       // (this.assets.open("medsJson.json").bufferedReader().use { it.readText()})
   list = Json.decodeFromString<List<Medicine>>(file)
    }
    if(isLoading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    else {
        LazyColumn(
            state = state,
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(2.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(items = list) { med ->
                MedListItem(med = med) { medId -> action(medId) }
            }
        }
    }
}
@Composable
fun MedListItem (
    med : Medicine,
    action : (String) -> Unit
    //implementable onClick action
)
{
 Card( shape = RectangleShape,
     modifier = Modifier
     .fillMaxWidth()
     .clickable { action(Json.encodeToString(med)) }
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
                 text = med.name,
                 fontSize = 30.sp,
                 textAlign = TextAlign.Start
             )
             Text(
                 text = "${med.name} is a very cool medication",
                 fontSize = 15.sp
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