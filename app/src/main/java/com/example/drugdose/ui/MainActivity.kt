package com.example.drugdose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.drugdose.data.AppDatabase
import com.example.drugdose.data.AppRepo
import com.example.drugdose.data.Medicine
import com.example.drugdose.ui.theming.AppTheme
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {

    //private  val viewModel : SharedViewModel = ViewModelProvider(this,fact)[SharedViewModel::class.java]
    //plus button sequence : main -> list of profiles -> if new profile {form} -> medicine list ->  if conflict {alert} else >(handle back button to main) result (return to main {flag activity clear top?})
    override fun onCreate(savedInstanceState: Bundle?) {
        val file = (this.assets.open("medsJson.json").bufferedReader().use { it.readText()})
        val list = Json.decodeFromString<List<Medicine>>(file)
        val database = AppDatabase.getDataBase(this)
        val repository = AppRepo(database.appDao(),list)
        val fact = SharedViewModelFactory(repository)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel : SharedViewModel = viewModel(factory = fact)
            val uiState by viewModel._uiState.collectAsStateWithLifecycle()
            AppTheme {
                Surface(){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                        )
                    {
                        AppNav(uiState,viewModel)
                    }

                }

            }

        }
    }
}

@Composable
fun AppNav(uiS : UiState,viewModel: SharedViewModel) {
    val navControl = rememberNavController()
    NavHost(
        navController = navControl,
        startDestination = "DoseMain"
    ) {
        composable("DoseMain") {
            DoseMain(
                plusAction = { navControl.navigate("ProfilesToMeds") },
                onInfo = { navControl.navigate("Info") },
                onProfiles = { navControl.navigate("Profiles") },
                onMeds = { navControl.navigate("MedList") }
            )
        }
        composable("MedList") {
            ListMed(toResult = false, ontoResult = {}, uiState = uiS, viewModel= viewModel )
        }
        composable("MedListToResult") {
            ListMed(toResult = true, ontoResult = { navControl.navigate("Result") }, uiState = uiS,viewModel= viewModel)
        }
        composable("Info") {
            Info()
        }
        composable("ProfilesToMeds") {
            Profiles(
                uiState = uiS,
                clickAct = { navControl.navigate("MedListToResult") },
                onNew = { navControl.navigate("FormToMeds") },
                viewModel= viewModel
            )
        }
        composable("Profiles") {
            Profiles(
                clickAct = { navControl.navigate("ProfileDetail")},
                onNew = { navControl.navigate("Form") },
                uiState = uiS,
                viewModel= viewModel
            )
        }
        composable("ProfileDetail") {
            ProfileDetail(uiS,viewModel)
        }

        composable("Result") {
            Result(
                viewModel = viewModel,
                uiState = uiS,
                onClickAct = {navControl.navigate("DoseMain")}
            )
        }
        composable("FormToMeds") {
            Form(toMedList = true, clickAct = { navControl.navigate("MedListToResult") },uiS,  viewModel= viewModel)
        }
        composable("Form") {
            Form(toMedList = false, clickAct = { navControl.popBackStack()}, uiS, viewModel= viewModel)
        }

    }
}
