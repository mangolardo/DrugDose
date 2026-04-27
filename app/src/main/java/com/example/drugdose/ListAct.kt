package com.example.drugdose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.drugdose.ui.MedList

//is activity
class ListAct : ComponentActivity() {
    // lateinit var drugModel : DrugDoseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  val activity = this
        // drugModel() = ViewModelProvider(this)[DrugDoseViewModel::class.java]

//        val file =
//            (this.assets.open("medsJson.json").bufferedReader().use { it.readText()})
//
//       val list = Json.decodeFromString<List<Medicine>>(file)

        // drugModel = DrugDoseViewModel(list)
//             val list2 = listOf(Medicine("11", "med1"), Medicine("22", "med2"), Medicine("33", "med3"))


        setContent {


            //val uiState by viewModel.uistate.collectAsStateWithLifecycle()

            val medLazyListState = rememberLazyListState()
            Surface(tonalElevation = 5.dp) {

                MedList(
                    json = this.assets.open("medsJson.json"),
                    action = { medId -> switchToForm(this, medId) },
                    state = medLazyListState
                )
            }
        }
        }

    }