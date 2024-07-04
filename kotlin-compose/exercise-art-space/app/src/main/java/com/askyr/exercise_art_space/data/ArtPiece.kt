package com.askyr.exercise_art_space.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArtPiece(
    @DrawableRes val artImageRes: Int,
    @StringRes val title: Int,
    @StringRes val artist: Int,
    @StringRes val year: Int
)
