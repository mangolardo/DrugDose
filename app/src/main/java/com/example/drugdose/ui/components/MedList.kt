package com.example.drugdose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.drugdose.data.Medicine

@Composable
fun MedList(
    meds : List<Medicine>,
    action : (String) -> Unit ={},
    state : LazyListState
){
    LazyColumn(
        state = state,
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(20.dp)
    ) {
        items( items= meds ) { med ->
            MedListItem(med = med){ medId -> action(medId)}
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
     .clickable { action(med.id) }
 )
 {
  Text(text = "meow")
 }
}