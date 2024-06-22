package com.askyr.exercise_task_manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.askyr.exercise_task_manager.ui.theme.ExercisetaskmanagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExercisetaskmanagerTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
                Surface {
                    TaskComplete(
                        taskStatus = stringResource(id = R.string.task_status),
                        acknowledgement = stringResource(id = R.string.acknowledgement)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun TaskComplete(taskStatus: String, acknowledgement: String, modifier: Modifier = Modifier) {
    val loadedPainter = painterResource(id = R.drawable.ic_task_completed)
    Column(
        modifier = modifier.fillMaxSize(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = loadedPainter, contentDescription = null)
        Text(
            text = taskStatus,
            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(top = 24.dp, bottom = 8.dp)
        )
        Text(
            text = acknowledgement,
            fontSize = 16.sp,
//            textAlign = TextAlign.Center
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExercisetaskmanagerTheme {
//        Greeting("Android")
        TaskComplete(
            taskStatus = stringResource(id = R.string.task_status),
            acknowledgement = stringResource(id = R.string.acknowledgement)
        )
    }
}