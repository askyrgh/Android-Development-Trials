@file:OptIn(ExperimentalMaterial3Api::class)

package com.askyr.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.askyr.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Lemonade(modifier = Modifier)
            }
        }
    }
}

@Composable
fun Lemonade(modifier: Modifier) {
    var appState by remember {
        mutableIntStateOf(1)
    }
    var btnClicks by remember {
        mutableIntStateOf(0)
    }
    var clickTarget by remember {
        mutableIntStateOf(1)
    }
    val loadedPainter : Painter
    val loadedContentDes : String
    val loadedTextContent: String

    when (appState) {
        1 -> {
            loadedPainter = painterResource(id = R.drawable.lemon_tree)
            loadedContentDes = stringResource(id = R.string.c_des_1)
            loadedTextContent = stringResource(id = R.string.msg_1)
        }
        2 -> {
            loadedPainter = painterResource(id = R.drawable.lemon_squeeze)
            loadedContentDes = stringResource(id = R.string.c_des_2)
            loadedTextContent = stringResource(id = R.string.msg_2)
        }
        3 -> {
            loadedPainter = painterResource(id = R.drawable.lemon_drink)
            loadedContentDes = stringResource(id = R.string.c_des_3)
            loadedTextContent = stringResource(id = R.string.msg_3)
        }
        else -> {
            loadedPainter = painterResource(id = R.drawable.lemon_restart)
            loadedContentDes = stringResource(id = R.string.c_des_4)
            loadedTextContent = stringResource(id = R.string.msg_4)
        }
    }
    TopAppBar(
        title = {
            Text(
                text = "Lemonade",//stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = modifier.fillMaxWidth()
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Yellow,
        )
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(Color(0xFFC3ECD2)),
            shape = RoundedCornerShape(25),
            onClick = {
            when (appState) {
                0,1,3 -> {
                    if(appState == 1)
                        clickTarget = (2..4).random()
                    appState++
                    appState %= 4
                }
                else -> {
                    btnClicks++
                    if(btnClicks == clickTarget) {
                        btnClicks = 0
                        appState++
                        appState %= 4
                    }
                }
            }
        }) {
            Image(
                painter = loadedPainter,
                contentDescription = loadedContentDes
            )
        }
        Spacer(modifier = modifier.size(32.dp))
        Text(
            text = loadedTextContent,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        Lemonade(Modifier)
    }
}