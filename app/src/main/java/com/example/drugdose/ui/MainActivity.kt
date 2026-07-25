package com.example.drugdose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.drugdose.ui.theming.AppTheme

class MainActivity : ComponentActivity() {

    //plus button sequence : main -> list of profiles -> if new profile {form} -> medicine list ->  if conflict {alert} else >(handle back button to main) result (return to main {flag activity clear top?})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                AppNav()
            }

        }
    }
}

@Composable
fun AppNav() {
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
            ListMed(toResult = false, ontoResult = {})
        }
        composable("MedListToResult") {
            ListMed(toResult = true, ontoResult = { navControl.navigate("Result") })
        }
        composable("Info") {
            Info()
        }
        composable("ProfilesToMeds") {
            Profiles(
                toMedList = true,
                ontoMedList = { navControl.navigate("MedList") },
                onNew = { navControl.navigate("FormToMeds") },
                isEdit = false
            )
        }
        composable("Profiles") {
            Profiles(
                toMedList = false,
                ontoMedList = {},
                onNew = { navControl.navigate("Form") },
                isEdit = false
            )
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
