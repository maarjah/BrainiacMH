package com.example.brainiac

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.brainiac.ui.theme.BrainiacTheme



class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BrainiacTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF000000)
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Splash") {
                        composable("Splash") {
                            SplashScreen(navController = navController)}

                        composable("WeekGrid") {
                            MyApp(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(navController: NavController) {
    val navController = rememberNavController()
    Scaffold(bottomBar = { MyBottomNavigation(navController = navController) }) {
        Box(Modifier.padding(it)) {
            NavHost(navController = navController, startDestination = WeekGrid.route) {
                composable(WeekGrid.route) {
                    WeekGrid(navController = navController)
                }
                composable(CheckGoals.route) {
                    CheckGoals(navController = navController)
                }
                composable(Progress.route) {
                    Progress(navController = navController)
                }
            }
        }
    }

}