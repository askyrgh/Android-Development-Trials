package com.askyr.exercise_art_space.data

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.askyr.exercise_art_space.R

object DataSource {
    val artPieces = listOf(
        ArtPiece(
            R.drawable.sailing_under_the_bridge,
            R.string.art_sailing_under_the_bridge,
            R.string.artist_kat_kuan,
            R.string.year_2017
        ),
        ArtPiece(
            R.drawable.still_life_of_blue_rose_and_other_flowers,
            R.string.still_life_of_blue_roses_and_other_flowers,
            R.string.artist_owen_scott,
            R.string.year_2021
        ),
    )
}