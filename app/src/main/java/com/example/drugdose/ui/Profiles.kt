package com.example.drugdose.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.drugdose.R

//is function with handleback()
@Composable
fun Profiles(toMedList:Boolean, ontoMedList: () -> Unit , onNew: () -> Unit, isEdit: Boolean){
if(toMedList){
    //profile screen without edit options  but with new option, when profile is selected or created
    //navigate directly to medlist
    Button(
        onClick = ontoMedList
    ) {}
}
else {
    //profile screen with edit(icon or on selection) and new options(confirm brings back to profiles frag)
}
}