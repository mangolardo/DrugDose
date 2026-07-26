package com.example.drugdose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.drugdose.ui.theming.AppTheme

class MainActivity : ComponentActivity() {
    val database = AppDatabase.getDataBase(this)
    val repository = AppRepo(database.appDao())
    val fact = SharedViewModelFactory(repository)
    //private  val viewModel : SharedViewModel = ViewModelProvider(this,fact)[SharedViewModel::class.java]
    //plus button sequence : main -> list of profiles -> if new profile {form} -> medicine list ->  if conflict {alert} else >(handle back button to main) result (return to main {flag activity clear top?})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel : SharedViewModel = viewModel(factory = fact)
            val uiState by viewModel._uiState.collectAsStateWithLifecycle()
            AppTheme {
                Surface(){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(5.dp)
                        )
                    {
                        AppNav(uiState)
                    }

                }

            }

        }
    }
}

@Composable
fun AppNav(uiS : UiState) {
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
            ListMed(toResult = false, ontoResult = {}, uiState = uiS)
        }
        composable("MedListToResult") {
            ListMed(toResult = true, ontoResult = { navControl.navigate("Result") }, uiState = uiS)
        }
        composable("Info") {
            Info()
        }
        composable("ProfilesToMeds") {
            Profiles(
                uiState = uiS,
                clickAct = { navControl.navigate("MedList") },
                onNew = { navControl.navigate("FormToMeds") }
            )
        }
        composable("Profiles") {
            Profiles(
                uiState = uiS,
                clickAct = { navControl.navigate("ProfileDetail")},
                onNew = { navControl.navigate("Form") }
            )
        }
        composable("ProfileDetail") {
            ProfileDetail()
        }

        composable("Result") {
            Result()
        }
        composable("FormToMeds") {
            Form(toMedList = true, ontoMedList = { navControl.navigate("MedList") })
        }
        composable("Form") {
            Form(toMedList = false, ontoMedList = {})
        }

    }
}
