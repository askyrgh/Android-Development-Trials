package com.example.firstkotlinapp

fun main() {
//    LambdaExpressionDemo()
//    unsafeCastDemo()
//    safeCastDemo()
}

// Lambda Expression
fun LambdaExpressionDemo() {
    // Lamda expression to return a sum of two integers
    val sum1: (Int, Int) -> Int = {a: Int, b: Int -> a+b}
    println(sum1(2,3))

    val sum2 = {a:Int, b:Int -> println(a+b) }
    sum2(2,3)
}

fun unsafeCastDemo() {
    val obj: Any? = null
    val str: String = obj as String
    println(str)    // Error
}

fun safeCastDemo() {
    val location: Any = "Kotlin"

    val safeString: String? = location as? String
    val safeInt: Int? = location as? Int
    println(safeString)

    println(safeInt)
}