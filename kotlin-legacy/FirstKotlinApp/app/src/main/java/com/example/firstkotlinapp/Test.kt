package com.example.firstkotlinapp

import java.util.Random

fun main(args: Array<String>) {
//    println("Hello ${args[0]}");
    feedTheFish();
}

// feed the fish example
fun feedTheFish() {
    val day = randomDay();
    val food = fishFood(day);
    println("Today is $day when fish eats $food");
}

fun randomDay(): String {
    val week = arrayOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday");
    return week[Random().nextInt(week.size)];
}

fun fishFood(day: String): String {
    var food = "";
    when(day) {
        "monday" ->
            food = "flakes"
        "tuesday" ->
            food = "pellets"
        "wednesday" ->
            food = "redworms"
        "thursday" ->
            food = "granules"
        "friday" ->
            food = "mosquitoes"
//        "saturday" ->
//            food = "lettuce"
//        "sunday" ->
//            food = "plankton"
        else ->
            food = "nothing"
    }
    return food;
}

