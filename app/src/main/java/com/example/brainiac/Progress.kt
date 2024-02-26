package com.example.brainiac

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.brainiac.ui.theme.gradientColors

//Corresponds to Activity 4
@Composable
fun Progress(navController: NavController) {
    //initializing context
    val context = LocalContext.current
    //instantiating DataStore
    val dataStore = DataStoreManager(context)
    //getting values from DataStore for counting clicks of "Yes" and "Confirm" from UI
    val savedCountYes = dataStore.getCountYesData.collectAsState(initial = -111)
    val savedCountConfirm = dataStore.getCountConfirmData.collectAsState(initial = -111)
    //variables for counting "Yes" and "Confirm" clicks in UI, remembering state in case of recompose
    var countYes by rememberSaveable {
        mutableIntStateOf(savedCountYes.value)
    }
    if(countYes == -111) {
        countYes = savedCountYes.value
    }

    var countConfirm by rememberSaveable {
        mutableIntStateOf(savedCountConfirm.value)
    }
    if(countConfirm == -111) {
        countConfirm = savedCountConfirm.value
    }

    //calculating display size of the brain image in accordance to the progress
    var displaySize = DisplaySize().calculateDisplaySize(countYes)

    //creating UI
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF000000),
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Row {
                UpperPanel()
            }
            Row {
                Text(
                    text = stringResource(id = R.string.weekly_progress).uppercase() + ": day $countConfirm".uppercase(),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colors = gradientColors
                        )
                    ),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 5.sp,
                    modifier = Modifier.padding(
                        start = 20.dp,
                        top = 40.dp,
                        bottom = 20.dp,
                        end = 20.dp
                    )
                )
            }
            Row {
                DividingLine()
            }
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 30.dp, top = 30.dp, end = 30.dp)
                    .fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.brain_silhouette),
                    contentDescription = "drawing of a brain silhouette",
                    modifier = Modifier
                        .fillMaxWidth(),
                )
                Image(
                    alignment = Alignment.Center,
                    painter = painterResource(id = R.drawable.brain),
                    contentDescription = "drawing of a human brain",
                    modifier = Modifier
                        .fillMaxWidth(displaySize)
                )

            }
        }
    }

}