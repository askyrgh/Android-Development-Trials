package com.example.firstkotlinapp.testapp

/*
// abstract and interface example
abstract class AquariumFish {
    abstract val color: String;
}

interface FishAction {
    fun eat();
}

class Shark: AquariumFish(), FishAction {
    override val color = "grey"
    override fun eat() {
        println("hunt and eat fish")
    }
}

class Plecostomus: AquariumFish(), FishAction {
    override val color = "gold"
    override fun eat() {
        println("eat algae")
    }
}
*/

// interface delegation example
interface FishAction {
    fun eat()
}
interface FishColor {
    val color: String;
}

// standard interface delegation
//class Plecostomus: FishAction, FishColor by GoldColor {
//    override fun eat() {
//        println("eat algae")
//    }
//}

// interface delegation with constructor
class Plecostomus(fishColor: FishColor = GoldColor): FishAction by PrintingFishAction("algae"), FishColor by fishColor

class Shark: FishAction, FishColor {
    override val color = "grey"
    override fun eat() {
        println("hunt and eat fish")
    }
}

// singleton in Kotlin
object GoldColor : FishColor {
    override val color = "gold"
}

// class with constructor for instantiation to use interface delegation
class PrintingFishAction(val food: String): FishAction {
    override fun eat() {
        println("eat $food");
    }
}

enum class Color(val rgb: Int) {
    RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF);
}

enum class Nums(val num: Int) {
    num1(1), num2(2), num3(3)
}