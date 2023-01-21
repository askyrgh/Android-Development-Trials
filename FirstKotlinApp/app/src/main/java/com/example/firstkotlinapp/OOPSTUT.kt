package com.example.firstkotlinapp

fun main() {
    var sky: Person = Person("SKY", "ROY")
    var def: Person = Person()

    myFunction(9)

    sky.stateHobby()
    sky.hobby = "gaming"
    sky.stateHobby()

    var k: Key = Key()

}

class Person constructor(firstName:String = "FirstName", lastName: String = "LastName"){
    // Example of a class

    // Member Variables - Properties
    var age: Int? = null
    var hobby: String = "painting"
    var firstName: String? = null

    // Member Functions - Methods
    fun stateHobby(){
        println("My hobby is $hobby")
    }

    // Initializer block
    init {
        println("Initialized a new Person with FN: ${firstName} & LN: ${lastName}")
        this.firstName = firstName
    }

    // Member Constructor - Secondary constructor
    constructor(firstName: String, lastName: String, age: Int) : this(firstName, lastName) {
        // returning an object by use of default constructor
        // via passing the parameters as arguments of default constructor
        // after initializing the respective member variables by the parameters

        this.age = age
        print("Initialized a new Person with FN: $firstName LN: $lastName AGE: $age")
    }
}

fun myFunction(a: Int) {
    var a = 10
    println("a = $a")
}

class Key{
    var keyId: Int? = null
    
    init {
        println("Key ID: $keyId")
    }
}