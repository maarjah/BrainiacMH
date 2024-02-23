package com.example.brainiac

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.brainiac.ui.theme.LightYellow


@Composable
fun MyBottomNavigation(navController: NavController) {
    val destinationList = listOf<Destinations>(
        WeekGrid,
        CheckGoals,
        Progress
    )
    val selectedIndex = rememberSaveable {
        mutableStateOf(0)
    }
    NavigationBar(containerColor = LightYellow
    ) {
        destinationList.forEachIndexed { index, destination ->
            NavigationBarItem(
                label = {
                    Text(
                        text = destination.title,
                        color = Color(0xFF000000)
                    )
                },

                icon = {
                    Icon(imageVector = destination.icon, contentDescription = destination.title)
                },
                selected = index == selectedIndex.value,
                onClick = {
                    selectedIndex.value = index
                    navController.navigate(destinationList[index].route) {
                        popUpTo(WeekGrid.route)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}




