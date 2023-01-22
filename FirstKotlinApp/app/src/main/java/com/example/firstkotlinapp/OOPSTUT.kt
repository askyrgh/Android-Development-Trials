package com.example.firstkotlinapp

import java.lang.IllegalArgumentException

fun main() {
    scopeAndShadowingDemo(9)

    constructorDemo()

    lateinitDemo()

    DataClassDemo()
}

// Scope and shawdowing example
fun scopeAndShadowingDemo(a: Int) {
    var a = 10
    println("a = $a")
}

// Constructor example
fun constructorDemo() {
    var sky: Person = Person("SKY", "ROY")
    var def: Person = Person()
}

class Person constructor(firstName:String = "FirstName", lastName: String = "LastName"){
    // Example of a class

    // Initializer block
    init {
        println("Initialized a new Person with FN: ${firstName} & LN: ${lastName}")
    }
}

// lateinit example
fun lateinitDemo() {
    var myCar = Car()
    println(myCar.owner)
    println("brand is ${myCar.brand}")
    myCar.topSpeed = 200f
    println("max speed is ${myCar.topSpeed}")
    println("model is ${myCar.model}")
}

class Car() {
    lateinit var owner : String // doesn't need to be initialized immediately
    val brand : String = "BMW"
        // custom getter
        get() {
            return field.lowercase()
        }

    var topSpeed: Float = 250f
        // field keyword is used to avoid direct use of variable which will lead to recursive calls causing stack overflow
        get() = field
        // custom setter
        // value is the variable that stores the data passed as argument and used to assign to the respective field
        set(value) {
            field = if(value>0) value else throw IllegalArgumentException("Max Speed can't be less than zero")
        }

    var model: String = "Series 3 Gran Limousine"
        private set

    init {
        owner = "SKY"
        model = "3GL"
    }
}

// Data Class example
fun DataClassDemo() {
    var user1 = User(1, "SKY")
    val name = user1.name
    println(name)
    user1.name = "AKASH"

    val user2 = User(2, "RAHUL")

    println(user1 == user2)

    // object copying
    val updatedUser = user1.copy(name = "ASKYR")
    println(updatedUser)

    println(user1)

    // component fetching
    println(updatedUser.component1())   // prints 1
    println(updatedUser.component2())   // prints ASKYR

    // deconstruction
    val (id_copy, name_copy) =  updatedUser
    println("id=$id_copy")
    println("name=$name_copy")
}

data class User(val id: Long, var name: String)