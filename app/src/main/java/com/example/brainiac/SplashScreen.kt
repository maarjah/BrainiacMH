package com.example.brainiac

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.brainiac.ui.theme.gradientColors
import kotlinx.coroutines.delay

/*
Corresponds to Activity 1. For displaying a splash screen when starting the app.
After that navigating to the Composable "SetGoals"
 */
@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1=true) {
       delay(3000)
        //clearing backstack
        navController.popBackStack()
        navController.navigate("SetGoals")
    }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF000000)
        ) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.app_name).uppercase(),
                    fontSize = 50.sp,
                    letterSpacing = 10.sp,
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colors = gradientColors
                        )
                    ),
                )
                Image(
                    painter = painterResource(id = R.drawable.brain),
                    contentDescription = "drawing of a human brain",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp)
                )
            }
        }
}
