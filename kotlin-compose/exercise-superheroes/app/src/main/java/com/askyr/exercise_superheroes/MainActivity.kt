package com.askyr.exercise_superheroes

import ExerciseSuperheroesTheme
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.askyr.exercise_superheroes.model.HeroesRepository

//import com.askyr.exercise_superheroes.ui.theme.ExerciseSuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExerciseSuperheroesTheme {
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
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SuperHeroesPreview() {
    ExerciseSuperheroesTheme {
        SuperHeroList(superHeroes = HeroesRepository.heroes, modifier = Modifier)
    }
}