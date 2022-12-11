package com.example.firstkotlinapp

fun main() {
      // Variable declaration
      var name1 = "SKY"
//      print("Hello " + name1)

      val name2 = "SKY"
//      name2 = "Akash" // Illegal statement
//      print("\nHello " + name2)

      // TODO: Update headings according to topic

      // Type Inference example
      var age = 24;

      // Integer TYPES: Byte(8 bit), Short(16 bit), Int(32 bit), Long(64 bit)
      val myByte: Byte = 16
      val myShort: Short = 124
      val myInt: Int = 1343151113
      val myLong: Long = 1221414124242141225

      // Floating point number TYPES: Float, Double
      val myFloat: Float = 0.5f
      val myDouble: Double = 3.1415926535

      // Booleans
      var isAvailable: Boolean = false
      isAvailable = true

      // Characters
      var letterChar = 'A'
      var digitChar =  '$'

      // Strings
      val myString: String = "Hello there, how's it going?"
      var firstCharInStr: Char = myString[0] // returns 'H'

//      print("\nFirst Character $firstCharInStr");

      // Operators
      // Arithmetic operators (+, -, *, /, %)
      var result = 5 + 3
      println("5+3 = ${result}")

      // Compound Assignment operators (+=, -=, *=, /=, %=)
      result /= 2
      println(result)

      // Comparison operator (==, !=, >, <, >=, <=)
      val isNotEqual = 5!=3
      println("5 is not equal to 3: ${isNotEqual}")

      println("5 is less than 3: ${5<3}")
      println("5 is greater than 3: ${5>3}")
      println("5 is less than or equal to 3: ${5<=3}")
      println("5 is greater than or equal to 3: ${5>=3}")

      // Conditional statements
      // if-else
      val ht1 = 179
      val ht2 = 180

      if(ht1 > ht2) {
            println("Use Raw Force")
      }
      else if (ht1 == ht2) {
            println("Use Technique")
      }
      else {
            println("Use Special Move")
      }

      // using if-else as expressions
      val yourAge = 19
      val drivingAge = 16
      val votingAge = 18
      val drinkingAge = 21

      val currentAge = if (yourAge >= drinkingAge) {
            println("You may caste your vote, drink, and drive in India");
            drinkingAge
      }
      else if (yourAge >= votingAge) {
            println("You may caste your vote, and drive in India");
            votingAge
      }
      else if (yourAge >= drivingAge) {
            println("You may drive now in India");
            drivingAge
      }
      else {
            println("You're too young")
            yourAge
      }

      println("Current Age: ${currentAge}")

      // using when statement
      var season = 3
      when (season) {
            1 -> println("Summer")
            2 -> println("Spring")
            3 -> {
                  println("Autumn")
                  println("Fall")
            }
            4 -> println("Winter")
            else -> {
                  println("Invalid season")
            }
      }

      var month = 3
      when (month) {
            in 2..3 -> println("Spring")
            in 4..7 -> println("Summer")
            in 8..10 -> println("Autumn")
            in 11..12 -> println("Winter")
            else -> println("Winter")
      }

      when (age) {
            !in 1..20 -> println("You may caste your vote, drink, and drive in India")
            16, 17 -> println("You may drive now in India")
            in 18 .. 20 -> println("You may caste your vote, and drive in India")
      }

      var x : Any = 13.55
      when (x) {
            is Int -> print("$x is an Integer type")
            is Double -> print("$x is a Floating type")
            is String -> print("$x is a String type")
            else -> println("None of the above")
      }

      // loops
      // 1. while loop - standard
      // 2. do while loop - standard
      // 3. for loop

      var num = 0
      for (num in 1 .. 10) {
            print("$num ")
      }
      println("\n or")

      for (num in 0 until 10) {
            print("$num ")
      }
      println("\n or")

      num = 10
      for (num in 10 downTo 1 step 2) {
            print("$num ")
      }
      println("\n or")

      num = 20
      for (num in 20.downTo(1).step(2)) {
            print("$num ")
      }
      println("\n or")

      // break and continue

      for (i in 1 until 20) {
            print("${i} ")
            if(i/2 == 5) {
                  println("loop ends here")
                  break
            }
      }

      for (i in 1 until 20) {
            if(i/2 == 5) {
                  continue
            }
            print("${i} ")
      }
}
