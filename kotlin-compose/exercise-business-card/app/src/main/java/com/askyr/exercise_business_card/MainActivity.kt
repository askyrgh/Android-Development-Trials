package com.askyr.exercise_business_card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.askyr.exercise_business_card.ui.theme.ExercisebusinesscardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExercisebusinesscardTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
                Surface {
                    BusinessCard(
                        name = "Akash Roy",
                        position = "Software Development Engineer II",
                        mobile = "+919717382577",
                        handle = "@amiskyr",
                        email = "akash.rm.roy@gmail.com",
                        modifier = Modifier
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
fun BusinessCard(name: String,
                 position: String,
                 mobile: String,
                 handle: String,
                 email: String,
                 modifier: Modifier
) {
    val loadedPainter = painterResource(id = R.drawable.android_logo)
    val groupModifier = Modifier.padding(
        top = 8.dp,
        start = 8.dp,
        end = 8.dp
    )
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxHeight()
            .background(Color(0xFFD2E8D4))
    ) {
        Spacer(modifier = Modifier)
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = loadedPainter,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .background(Color(0xFF073042))
            )
            Text(
                text = name,
                fontSize = 35.sp
            )
            Text(
                text = position,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF237E51)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 60.dp)
                .fillMaxWidth()
        ) {
            ImageGroup(modifier = groupModifier)
            TextGroup(modifier = groupModifier, mobile = mobile, handle = handle, email = email)
        }

        /*
        Column() {
            TextWithIcon(
                providedPainter = painterResource(id = R.drawable.baseline_call_24),
                text = mobile
            )
            TextWithIcon(
                providedPainter = painterResource(id = R.drawable.baseline_call_24),
                text = handle
            )
            TextWithIcon(
                providedPainter = painterResource(id = R.drawable.baseline_call_24),
                text = email
            )
        }
         */
    }
}

@Composable
fun ImageGroup(
    modifier: Modifier
) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.baseline_call_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color(0xFF006D3B)),
            modifier = modifier
        )
        Image(
            painter = painterResource(id = R.drawable.baseline_share_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color(0xFF006D3B)),
            modifier = modifier
        )
        Image(
            painter = painterResource(id = R.drawable.baseline_email_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color(0xFF006D3B)),
            modifier = modifier
        )
    }
}

@Composable
fun TextGroup(
    modifier: Modifier,
    mobile: String,
    handle: String,
    email: String
) {
    Column {
        Text(
            text = mobile,
            modifier = modifier
        )
        Text(
            text = handle,
            modifier = modifier
        )
        Text(
            text = email,
            modifier = modifier
        )
    }
}

@Composable
fun TextWithIcon(providedPainter: Painter, text: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(painter = providedPainter, contentDescription = null)
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExercisebusinesscardTheme {
//        Greeting("Android")
        BusinessCard(
            name = "Akash Roy",
            position = "Software Development Engineer II",
            mobile = "+919717382577",
            handle = "@amiskyr",
            email = "akash.rm.roy@gmail.com",
            Modifier
        )
    }
}