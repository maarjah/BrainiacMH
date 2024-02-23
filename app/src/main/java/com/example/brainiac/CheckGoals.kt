package com.example.brainiac

//import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
fun CheckGoals(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = DataStoreManager(context)
    val savedCountOne = dataStore.getCountOneData.collectAsState(initial = -111)
    val savedCountTwo = dataStore.getCountTwoData.collectAsState(initial = -111)
    val savedCountYes = dataStore.getCountYesData.collectAsState(initial = -111)
    val savedCountConfirm = dataStore.getCountConfirmData.collectAsState(initial = -111)

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

    var radioStateOne by rememberSaveable {
        mutableStateOf(false)
    }
    var radioStateTwo by rememberSaveable {
        mutableStateOf(false)
    }

    fun clickConfirm() {
        if(countConfirm == 7) {
            countConfirm = 1
            countYes = 0
            if(radioStateOne) {
                countYes += 1
            }
            if(radioStateTwo) {
                countYes += 1
            }
        }else{
            countConfirm +=1
            if(radioStateOne) {
                countYes += 1
            }
            if(radioStateTwo) {
                countYes += 1
            }

        }

        if(radioStateOne && radioStateTwo){
            Toast.makeText(context, "GOOD JOB!", Toast.LENGTH_SHORT).show()
        }
        else if(radioStateOne || radioStateTwo){
            Toast.makeText(context, "TRY HARDER TOMORROW!", Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(context, "FIRST WORK HARD, THEN PLAY HARD!", Toast.LENGTH_SHORT).show()
        }


        navController.navigate(Progress.route)

        scope.launch {
            dataStore.saveConfirmData(countYes, countConfirm)
        }
    }


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
            Row(horizontalArrangement = Arrangement.Start) {
                Text(
                    text = stringResource(id = R.string.track_goals).uppercase(),
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.subject_one),
                    color = LightYellow,
                    fontSize = 16.sp,
                    letterSpacing = 3.sp,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 15.dp, bottom = 15.dp)
                        .fillMaxWidth(0.3f)
                )
                Text(
                    text = savedCountOne.value.toString(),
                    fontSize = 16.sp,
                    color = LightYellow,
                    modifier = Modifier.width(30.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.unit),
                    fontSize = 16.sp,
                    color = LightYellow
                )
                Text(
                    text = stringResource(id = R.string.yes).uppercase(),
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colors = gradientColors
                        )
                    ),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 5.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
                RadioButton(
                    selected = radioStateOne,
                    onClick = { radioStateOne = true },
                    modifier = Modifier
                        .semantics { contentDescription = "Yes" }
                        .padding(end = 10.dp)
                )
                Text(
                    text = stringResource(id = R.string.no).uppercase(),
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colors = gradientColors
                        )
                    ),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 5.sp
                )
                RadioButton(
                    selected = !radioStateOne,
                    onClick = { radioStateOne = false },
                    modifier = Modifier.semantics { contentDescription = "No" }
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.subject_two),
                    color = LightYellow,
                    fontSize = 16.sp,
                    letterSpacing = 3.sp,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 15.dp, bottom = 15.dp)
                        .fillMaxWidth(0.3f)
                )
                Text(
                    text = savedCountTwo.value.toString(),
                    fontSize = 16.sp,
                    color = LightYellow,
                    modifier = Modifier.width(30.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.unit),
                    fontSize = 16.sp,
                    color = LightYellow
                )
                Text(
                    text = stringResource(id = R.string.yes).uppercase(),
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colors = gradientColors
                        )
                    ),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 5.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
                RadioButton(
                    selected = radioStateTwo,
                    onClick = { radioStateTwo = true },
                    modifier = Modifier
                        .semantics { contentDescription = "Yes" }
                        .padding(end = 10.dp)
                )
                Text(
                    text = stringResource(id = R.string.no).uppercase(),
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colors = gradientColors
                        )
                    ),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 5.sp
                )
                RadioButton(
                    selected = !radioStateTwo,
                    onClick = { radioStateTwo = false },
                    modifier = Modifier.semantics { contentDescription = "No" }
                )
            }
            Row {
                DividingLine()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        clickConfirm()
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(LightPurple),
                    modifier = Modifier.padding(bottom = 40.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.confirm).uppercase(),
                        fontSize = 16.sp,
                        color = Color(0xFF000000),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
