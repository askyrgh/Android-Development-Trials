package com.askyr.exercise_art_space

import android.os.Bundle
import android.service.autofill.OnClickAction
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.askyr.exercise_art_space.data.ArtPiece
import com.askyr.exercise_art_space.data.DataSource
import com.askyr.exercise_art_space.ui.theme.ExerciseartspaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExerciseartspaceTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    ArtSpace(pieces = DataSource.artPieces)
//                }
                Surface {
                    ArtSpace(pieces = DataSource.artPieces)
                }
            }
        }
    }
}

@Composable
fun ArtSpace(pieces: List<ArtPiece>) {
    var index by remember {
        mutableStateOf(0)
    }
    ArtWork(
        artPiece = pieces[index],
        onClickPrevAction = { index = if(index == 0) pieces.size-1 else index-1 },
        onClickNextAction = { index = (index+1)%pieces.size },
        modifier = Modifier
    )
}

@Composable
fun ArtWork(
    artPiece: ArtPiece,
    onClickPrevAction: () -> Unit,
    onClickNextAction: () -> Unit,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier
            .height(80.dp)
            .background(Color.Green)
        )
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
                modifier = Modifier
        ) {
            Surface(
                onClick = { /*TODO*/ },
                shadowElevation = 16.dp
                ) {
                Image(
                    painter = painterResource(id = artPiece.artImageRes),
                    contentDescription = stringResource(id = artPiece.title),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(width = 360.dp, height = 480.dp)
                        .padding(32.dp)
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 8.dp
                )
        ) {
            val title = stringResource(id = artPiece.title)
            val artist = stringResource(id = artPiece.artist)
            val year = stringResource(id = artPiece.year)

            Column(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.accent_color))
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "$artist ($year)",
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ) {
                Button(
                    onClick = onClickPrevAction,
                    modifier = Modifier.width(140.dp)
                ) {
                    Text(text = stringResource(id = R.string.previous))
                }
                Button(
                    onClick = onClickNextAction,
                    modifier = Modifier.width(140.dp)
                ) {
                    Text(text = stringResource(id = R.string.next))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtWorkPreview() {
    ExerciseartspaceTheme {
//        ArtWork(artPiece = DataSource.artPieces[1],
//            modifier = Modifier
//        )
    }
}

//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ExerciseartspaceTheme {
//        Greeting("Android")
//    }
//}