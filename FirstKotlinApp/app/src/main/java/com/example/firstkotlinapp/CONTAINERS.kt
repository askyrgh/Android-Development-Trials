package com.example.firstkotlinapp

fun main() {
    arrayDemo()
}

// array
fun arrayDemo() {
    // array declaration
//    val numbers: IntArray = intArrayOf(1,2,3,4,5)
//    val numbers = intArrayOf(1,2,3,4,5)   // using type inference for constructor
    val numbers = arrayOf(1,2,3,4,5)    // using generic constructor that decides through type inference

    print(numbers)
}
