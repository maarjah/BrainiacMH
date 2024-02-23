package com.example.brainiac

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

interface Destinations {
    val route: String
    val title: String
    val icon: ImageVector
}

object WeekGrid: Destinations {
    override val route = "WeekGrid"
    override val title = "Goals"
    override val icon = Icons.Filled.Add
}

object CheckGoals: Destinations {
    override val route = "CheckGoals"
    override val title = "Track"
    override val icon = Icons.Filled.Check
}

object Progress: Destinations {
    override val route = "Progress"
    override val title = "Progress"
    override val icon = Icons.Filled.ThumbUp
}