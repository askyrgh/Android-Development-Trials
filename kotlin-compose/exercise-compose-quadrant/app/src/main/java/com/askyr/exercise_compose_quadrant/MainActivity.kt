package com.askyr.exercise_compose_quadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.askyr.exercise_compose_quadrant.ui.theme.ExercisecomposequadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExercisecomposequadrantTheme {
                Surface {
                    AllQuads(
                        modifier = Modifier
                    )
                }
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }

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
fun AllQuads(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier.weight(1f)
        ) {
            Quadrant(
                header = stringResource(id = R.string.title_first),
                content = stringResource(id = R.string.content_first),
                backgroundColor = Color(0xFFEADDFF),
                modifier = modifier.weight(1f)
            )
            Quadrant(
                header = stringResource(id = R.string.title_second),
                content = stringResource(id = R.string.content_second),
                backgroundColor = Color(0xFFD0BCFF),
                modifier = modifier.weight(1f)
            )
        }
        Row(
            modifier = modifier.weight(1f)
        ) {
            Quadrant(
                header = stringResource(id = R.string.title_third),
                content = stringResource(id = R.string.content_third),
                backgroundColor = Color(0xFFB69DF8),
                modifier = modifier.weight(1f)
            )
            Quadrant(
                header = stringResource(id = R.string.title_fourth),
                content = stringResource(id = R.string.content_fourth),
                backgroundColor = Color(0xFFF6EDFF),
                modifier = modifier.weight(1f)
            )
        }
    }
}

@Composable
fun Quadrant(
    header: String,
    content: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = header,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
        Text(
            text = content,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExercisecomposequadrantTheme {
        AllQuads(
            modifier = Modifier
        )
    }
}