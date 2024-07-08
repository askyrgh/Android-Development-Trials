package com.askyr.exercise_superheroes

import ExerciseSuperheroesTheme
import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.askyr.exercise_superheroes.model.Hero
import com.askyr.exercise_superheroes.model.HeroesRepository

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SuperHeroApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SuperHeroTopAppBar()
        }
    ) {
        it ->
        SuperHeroList(
            superHeroes = HeroesRepository.heroes,
            modifier = Modifier,
            contentPadding = it
        )
    }
}


@Composable
fun SuperHeroList(
    superHeroes: List<Hero>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(contentPadding = contentPadding) {
        itemsIndexed(superHeroes) { index, hero ->
            SuperheroItem(
                hero = hero,
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_med),
                        vertical = dimensionResource(id = R.dimen.padding_low)
                    )
            )
        }
    }
}

@Composable
fun SuperheroItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .clip(shape = RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_med))
                .fillMaxWidth()
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_low)))
            ) {
                Image(
                    painter = painterResource(id = hero.imageRes),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Row {
                Text(text = stringResource(id = R.string.app_name))
            }
        }
    )
}

@Composable
fun SuperHeroInfo(heroName: String, heroDesc: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = heroName,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = heroDesc,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun SuperHeroIcon(heroPainter: Painter, modifier: Modifier) {
    Card(modifier = modifier) {
        Image(
            painter = heroPainter,
            contentDescription = null,
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SuperHeroPreview() {
    SuperHeroApp()
//    SuperHeroList(superHeroes = HeroesRepository.heroes)
}

@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SuperHeroDarkPreview() {
    SuperHeroApp()
//    SuperHeroList(superHeroes = HeroesRepository.heroes)
//    ExerciseSuperheroesTheme(darkTheme = true) {
//    }
}
