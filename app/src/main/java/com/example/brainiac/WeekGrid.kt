package com.example.brainiac

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.brainiac.ui.theme.LightPurple
import com.example.brainiac.ui.theme.LightYellow
import com.example.brainiac.ui.theme.gradientColors
import kotlinx.coroutines.launch

@Composable
fun WeekGrid(navController: NavController) {
    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val dataStore = DataStoreManager(context)

    val savedCountOne = dataStore.getCountOneData.collectAsState(initial = -111)
    val savedCountTwo = dataStore.getCountTwoData.collectAsState(initial = -111)

    var count_one by rememberSaveable {
        mutableIntStateOf(savedCountOne.value)
    }

    if( count_one==-111 ) {
        count_one = savedCountOne.value
    }


    var count_two by rememberSaveable {
        mutableIntStateOf(savedCountTwo.value)
    }

    if( count_two==-111) {
            count_two = savedCountTwo.value
    }



    val min = 0
    val max = 60

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF000000)) {
        Column (
            modifier = Modifier.verticalScroll(rememberScrollState())
        ){

            Row {
                UpperPanel()
            }
            Row (horizontalArrangement = Arrangement.Start) {
                Text(
                    text = stringResource(id = R.string.daily_goals).uppercase(),
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colors = gradientColors
                        )
                    ),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 5.sp,
                    modifier = Modifier.padding(start=20.dp, top = 40.dp, bottom = 20.dp, end = 20.dp)
                )
            }
            Row {
                DividingLine()
            }
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = stringResource(id = R.string.subject_one),
                    color = LightYellow,
                    fontSize = 16.sp,
                    letterSpacing = 3.sp,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .fillMaxWidth(0.3f))
                TextButton(
                    onClick = {
                        if(count_one > min) {
                            count_one -= 5
                        }},
                    modifier = Modifier
                        .wrapContentHeight()
                ) {
                    Text(
                        text = "-",
                        color = LightYellow,
                        modifier = Modifier.wrapContentSize()
                    )
                }
                Text(
                    text = count_one.toString(),
                    fontSize = 16.sp,
                    color = LightYellow,
                    modifier = Modifier.width(30.dp),
                    textAlign = TextAlign.Center)
                TextButton(onClick = {
                    if(count_one < max){
                        count_one += 5
                    }}) {
                    Text(
                        text = "+",
                        color = LightYellow
                    )
                }
                Text(
                    text = stringResource(id = R.string.unit),
                    fontSize = 16.sp,
                    color = LightYellow)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.subject_two),
                    color = LightYellow,
                    fontSize = 16.sp,
                    letterSpacing = 3.sp,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .fillMaxWidth(0.3f))
                TextButton(
                    onClick = {
                        if(count_two > min){
                            count_two -= 5
                        }},
                    modifier = Modifier.wrapContentHeight()) {
                    Text(
                        text = "-",
                        color = LightYellow,
                        modifier = Modifier.wrapContentSize()
                    )
                }
                Text(
                    text = count_two.toString(),
                    fontSize = 16.sp,
                    color = LightYellow,
                    modifier = Modifier.width(30.dp),
                    textAlign = TextAlign.Center)
                TextButton(onClick = {
                    if(count_two < max){
                        count_two += 5
                    }}) {
                    Text(
                        text = "+",
                        color = LightYellow
                    )
                }
                Text(
                    text = stringResource(id = R.string.unit),
                    fontSize = 16.sp,
                    color = LightYellow)
            }
            Row {
                DividingLine()
            }
            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()){
                Button(shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(LightPurple),
                    modifier = Modifier.padding(bottom = 40.dp),
                    onClick = {
                        scope.launch {
                            dataStore.setCountOneData(count_one)
                            dataStore.setCountTwoData(count_two)

                        }

                        //Toast.makeText(context, "Weekly goals saved", Toast.LENGTH_SHORT).show()
                        navController.navigate(CheckGoals.route)
                    },
                ) {
                    Text(
                        text = stringResource(id = R.string.set_goals).uppercase(),
                        fontSize = 16.sp,
                        color = Color(0xFF000000),
                        fontWeight = FontWeight.Bold
                    )

                }

            }

        }
    }
}

