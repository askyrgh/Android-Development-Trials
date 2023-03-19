package com.example.firstkotlinapp

fun main() {
    LambdaExpressionDemo()
}

// Lambda Expression
fun LambdaExpressionDemo() {
    // Lamda expression to return a sum of two integers
    val sum1: (Int, Int) -> Int = {a: Int, b: Int -> a+b}
    println(sum1(2,3))

    val sum2 = {a:Int, b:Int -> println(a+b) }
    sum2(2,3)
}