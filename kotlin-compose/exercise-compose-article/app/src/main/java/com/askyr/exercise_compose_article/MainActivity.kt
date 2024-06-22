package com.askyr.exercise_compose_article

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.askyr.exercise_compose_article.ui.theme.ExercisecomposearticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExercisecomposearticleTheme {
                Surface {
                    DisplayArticle(
                        stringResource(id = R.string.article_header),
                        stringResource(id = R.string.article_context),
                        stringResource(id = R.string.article_content)
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
fun DisplayArticle(header: String, context: String, content: String, modifier: Modifier = Modifier) {
    val loadedPainter = painterResource(id = R.drawable.bg_compose_background)
    Column {
        Image(painter = loadedPainter, contentDescription = null)
        Text(
            text = header,
            textAlign = TextAlign.Start,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(16.dp)
        )
        Text(
            text = context,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                )
        )
        Text(
            text = content,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExercisecomposearticleTheme {
        Greeting("Android")
        DisplayArticle(
            stringResource(id = R.string.article_header),
            stringResource(id = R.string.article_context),
            stringResource(id = R.string.article_content)
        )
    }
}