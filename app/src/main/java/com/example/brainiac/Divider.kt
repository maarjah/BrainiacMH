package com.example.brainiac


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.brainiac.ui.theme.LightPurple

@Composable
fun DividingLine(){
        Divider(
            color = LightPurple,
            modifier = Modifier.padding(20.dp)
        )
}
