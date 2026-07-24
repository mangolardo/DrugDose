package com.example.drugdose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.drugdose.ui.AppContent
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
    ){
        composable("DoseMain")
        composable("ListContent")
        composable("Info")
        composable("Profiles")
        composable("Form")
        composable("Result")

    }
}
