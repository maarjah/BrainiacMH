package com.example.brainiac

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brainiac.ui.theme.LightPurple
import com.example.brainiac.ui.theme.gradientColors

//header element for Activites 2, 3, 4 consisting of logo and app name
@Composable
fun UpperPanel() {
    Column {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.brain),
                contentDescription = "drawing of a human brain",
                modifier = Modifier
                    .padding(20.dp)
                    .size(75.dp)
            )
            Text(
                text = stringResource(id = R.string.app_name).uppercase(),
                fontSize = 30.sp,
                letterSpacing = 10.sp,
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    )
                ),
            )

        }
        Row {
            DividingLine()
        }

    }
}