package com.example.firstkotlinapp

import java.lang.IllegalArgumentException
import kotlin.math.floor

fun main() {
    scopeAndShadowingDemo(9)

    constructorDemo()

    lateinitDemo()

    DataClassDemo()

//    InheritanceDemo()

    InterfaceDemo()

    typecastingDemo()
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
    var myCar = SimpleCar()
    println(myCar.owner)
    println("brand is ${myCar.brand}")
    myCar.topSpeed = 200f
    println("max speed is ${myCar.topSpeed}")
    println("model is ${myCar.model}")
}

class SimpleCar() {
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

//Inheritance Example

open class Vehicle {
    // properties
    // methods
}

//open class Car(val name: String, val brand: String){
//    // use of open keyword so the property can be overridden in derived class
//    open var range: Double = 0.0
//
//    // use of open keyword so the method can be overridden in derived class
//    open fun extendRange(amount: Double) {
//        if(amount > 0) {
//            range += amount
//        }
//    }
//
//    open fun drive(distance: Double) {
//        println("Drove for ${distance} KMs")
//    }
//}
//
//class ElectricCar(name: String, brand: String, batteryLife: Double) : Car(name, brand) {
//    var chargerType : String = "Type1"
//    override var range = batteryLife * 6
//
//    override fun drive(distance: Double) {
//        super.drive(distance)
//        println("Drove ${distance} KMs on electricity")
//    }
//
//    fun drive() {
//        println("Drove ${range} KMs on electricity")
//    }
//}
//
//fun InheritanceDemo() {
//    println()
//    println("---Inheritance Demo---")

//    var audiA3 = Car("A3", "Audi")
//    var teslaS = ElectricCar("S-Model", "Tesla", 85.0)
//
//    teslaS.toString()   // this is possible because all classes in Kotlin inherit the 'Any' Class as super class by default
//
//    teslaS.extendRange(100.0)   // this is possible for ElectricCar object because it inherits QuadCar
//
//    teslaS.chargerType = "Type2"
//
//    // Polymorphism - Ability to treat objects with similar traits in a common way
//    audiA3.drive(200.0)
//    teslaS.drive(200.0)
//}

// Interface example
interface Drivable {
    val maxSpeed : Double
    fun drive() : String
    fun brake() {
        println("The drivable is braking")
    }
}

// Implementation of maxSpeed property in Drivable
open class Car(override val maxSpeed : Double, val name: String, val brand: String) : Drivable{
    // use of open keyword so the property can be overridden in derived class
    open var range: Double = 0.0

    // use of open keyword so the method can be overridden in derived class
    open fun extendRange(amount: Double) {
        if(amount > 0) {
            range += amount
        }
    }

    // This drive() method's signature was not same as the Drivable drive()
    // therefore it isn't valid implementation of the former
    open fun drive(distance: Double) {
        println("Drove for ${distance} KMs")
    }

    // Implementation of drive() method in Drivable
    override fun drive(): String {
        return "driving the interface drive"
    }
}

class ElectricCar(maxSpeed: Double, name: String, brand: String, batteryLife: Double) : Car(maxSpeed, name, brand) {
    var chargerType : String = "Type1"
    override var range = batteryLife * 6

    override fun drive(distance: Double) {
        super.drive(distance)
        println("Drove ${distance} KMs on electricity")
    }

    override fun drive() : String {
        return "Drove ${range} KMs on electricity"
    }

    override fun brake() {
        super.brake()
        println("Brake inside of electric car")
    }
}

fun InterfaceDemo() {
    println()
    println("---Interface Demo---")

    var audiA3 = Car(200.0,"A3","Audi")
    var teslaS = ElectricCar(240.0,"S-Model","Tesla",85.0)

    teslaS.toString()   // this is possible because all classes in Kotlin inherit the 'Any' Class as super class by default

    teslaS.extendRange(100.0)   // this is possible for ElectricCar object because it inherits QuadCar

    audiA3.drive(200.0)
    teslaS.drive(200.0)

    teslaS.drive()
    teslaS.brake()

    audiA3.drive()
    audiA3.brake()
}

// Abstract Class example

// Concrete non abstract properties - name, origin, weight
abstract class Mammal(private val name: String, private val origin: String, private val weight: Double) {
    // Abstract properties (Must be overridden by subclass)
    abstract val maxSpeed: Double

    // Abstract methods (Must be overridden by subclass)
    abstract fun run()
    abstract fun breath()

    // Concrete Non-abstract methods
    fun displayDetails() {
        println(" Name: $name")
        println(" Origin: $origin")
        println(" Weight: $weight")
        println(" Max Speed: $maxSpeed")
    }
}

class Human(name: String, origin: String, weight: Double, override val maxSpeed: Double): Mammal(name, origin, weight) {
    override fun run() {
        println("Runs on 2 legs")
    }

    override fun breath() {
        println("Breathes through mouth or nose")
    }
}

class Elephant(name: String, origin: String, weight: Double, override val maxSpeed: Double): Mammal(name, origin, weight) {
    override fun run() {
        println("Runs on 4 legs")
    }

    override fun breath() {
        println("Breathes through the trunk")
    }
}

fun AbstractClassDemo() {
    println()
    println("---Abstract Class Demo---")

    val human = Human("Akash", "India", 74.0, 3.6)
    val elephant = Human("Rosy", "India", 5400.0, 11.2)

    human.breath()
    elephant.breath()

    human.run()
    elephant.run()
}

// Typecasting example

fun typecastingDemo() {
    val mixedTypeList: List<Any> = listOf("SKY", "ROY", 5, 6, 98, "askyr")

    for (e in mixedTypeList) {
        when(e) {
            is Int -> println("Integer: $e")
            is Double -> println("Double: $e with floor value: ${floor(e)}")
            is String -> println("String: $e of length: ")
            else -> println("Unknown type")
        }
    }

    // Smart Cast
    val obj1: Any = "I have a dream"
    if(obj1 !is String) {
        println("Not a String")
    }
    else {
        println("Found a String of length: {${obj1.length}")
    }

    // Explicit (unsafe) Casting using the "as" keyword - can go wrong
    val str1: String = obj1 as String
    println(str1. length)

    val obj2: Any = 1337
//    val str2: String = obj2 as String // shows error
//    println(str2)

    // Explicit (safe) casting using the "as?" keyword
    val obj3: Any = 1337
    val str3: String? = obj3 as? String // Works
    println(str3) // Prints null
}