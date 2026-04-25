package com.example.drugdose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.drugdose.data.Medicine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.File
import java.io.InputStream
import java.net.URI
import java.net.URL
import kotlin.io.path.toPath

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
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(20.dp)
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
 Card(modifier = Modifier
     .clickable { action(Json.encodeToString(med)) }
 )
 {
  Text(text = med.name)
 }
}