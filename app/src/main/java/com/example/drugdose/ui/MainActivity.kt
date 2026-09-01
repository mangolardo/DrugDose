package com.example.drugdose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.drugdose.data.AppDatabase
import com.example.drugdose.data.AppRepo
import com.example.drugdose.ui.theming.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        val database = AppDatabase.getDataBase(this, applicationScope)
        val repository = AppRepo(database.appDao())
        val fact = SharedViewModelFactory(repository)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel : SharedViewModel = viewModel(factory = fact)
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            AppTheme {
                Surface {
                Column(
                       modifier = Modifier.fillMaxWidth(),
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
        startDestination = "DoseMain",
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
            Form(
                toMedList = true,
                clickAct = { navControl.navigate("MedListToResult") },
                viewModel= viewModel
            )
        }
        composable("Form") {
            Form(toMedList = false, clickAct = { navControl.popBackStack()}, viewModel= viewModel)
        }

    }
}
