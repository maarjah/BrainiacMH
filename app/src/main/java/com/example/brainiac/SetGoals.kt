package com.example.brainiac

import android.widget.Toast
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


/*
Corresponds to Activity 2.
 */
@Composable
fun SetGoals(navController: NavController) {
    //variable for context
    val context = LocalContext.current
    //variable for scope for storing data in DataStore
    val scope = rememberCoroutineScope()
    //getting an instance of DataStore
    val dataStore = DataStoreManager(context)
    //initializing variables for countOne and countTwo data in DataStore for storing Reading and Math daily goal data
    val savedCountOne = dataStore.getCountOneData.collectAsState(initial = -111)
    val savedCountTwo = dataStore.getCountTwoData.collectAsState(initial = -111)
    //initializing variables count_one and count_two with values stored in DataStore to take user input from UI and remembering state in case recompose happens.
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


    //initializing min and max values for countOne and countTwo data for user input from GUI counter elements for Reading and Math goals
    val min = 0
    val max = 60
    //crating UI
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
                        //counting from 0, step 5
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
                    //counting up to 60, step 5
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
                        //counting from 0, step 5
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
                    //counting up to 60, step 5
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
                        //storing the user input from UI for Reading and Math goals in DataStore
                        scope.launch {
                            dataStore.setCountOneData(count_one)
                            dataStore.setCountTwoData(count_two)

                        }
                        //confirming goals are saved
                        Toast.makeText(context, "Weekly goals saved", Toast.LENGTH_SHORT).show()
                        //navigating to Activity3 to track daily goals
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

