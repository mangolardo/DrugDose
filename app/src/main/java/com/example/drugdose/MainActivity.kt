package com.example.drugdose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import com.example.drugdose.ui.AppContent
import com.example.drugdose.ui.theming.AppTheme

class MainActivity : ComponentActivity() {

    //plus button sequence : main -> list -> form (keep stack until here) -> if conflict {alert} else >(handle back button to main) result (return to main {flag activity clear top?})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(tonalElevation = 5.dp) {
                    AppContent(this, ListAct::class.java)
                }
            }

        }
    }
}
//@Composable
//fun AppContent(activity : Activity, nextClass : Class<*> ){
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        DoseMain(activity,nextClass)
//    }
////add navigation routes for navbar
//}
//
//@Composable
//fun DoseMain(activity : Activity, nextClass : Class<*>){
//    Text(text = "What's your dose?")
//    Button(onClick = {
//        switchAct(activity, nextClass)
//    })
//    {
//        Text(
//            text = "+"
//        )
//    }
//}
