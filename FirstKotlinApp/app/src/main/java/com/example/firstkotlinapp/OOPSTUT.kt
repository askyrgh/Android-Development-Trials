package com.example.firstkotlinapp

fun main() {
    var sky: Person = Person("SKY", "ROY")
    var def: Person = Person()

    myFunction(9)
}

class Person constructor(firstName:String = "FirstName", lastName: String = "LastName"){
    // Example of a class

    // Initializer block
    init {
        println("Initialized a new Person with FN: ${firstName} & LN: ${lastName}")
    }
}

fun myFunction(a: Int) {
    var a = 10
    println("a = $a")
}