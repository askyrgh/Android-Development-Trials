package com.example.firstkotlinapp

fun main() {
      // Variable declaration
      var name1 = "SKY"
      print("Hello " + name1)

      val name2 = "SKY"
//      name2 = "Akash" // Illegal statement
      print("Hello " + name2)

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

      print("First Character $firstCharInStr");
}
