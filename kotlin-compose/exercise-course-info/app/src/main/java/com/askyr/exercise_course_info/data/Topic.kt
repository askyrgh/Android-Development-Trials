package com.askyr.exercise_course_info.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val courseName: Int,
    val courses: Int,
    @DrawableRes val imageResourceId: Int,
)