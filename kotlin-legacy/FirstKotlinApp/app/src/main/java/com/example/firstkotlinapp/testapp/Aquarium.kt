package com.example.firstkotlinapp.testapp

import java.lang.Math.PI

open class Aquarium(var length: Int = 100, var width: Int = 20, open var height: Int = 40){

    open var volume: Int
        get() = width * height * length / 1000
        set(value) {
            height = (value * 1000) / (width * length);
            println("height changed to $height");
        }

    open val shape = "cuboid"
    open var waterCapacity: Double = 0.0
        get() = 0.9 * volume;

    init {
        println("aquarium initializing")
    }
    constructor(numberOfFish: Int) : this() {
        // secondary constructor
    }
    fun printSize() {
        println("Shape: $shape");
        println("Width: $width cm\nHeight: $height cm\nLength: $length cm\nVolume: $volume l");
        println("Volume: $volume liters Water: $waterCapacity liters (${waterCapacity / volume * 100.0}% full)")
    }
}

class TowerTank(override var height: Int, var diameter: Int) : Aquarium(height = height, width = diameter, length = diameter) {
    override var volume: Int
        get() = (width/2 * length/2 * height/1000 * PI).toInt();
        set(value) {
            height = ((value * 1000 / PI) / (width/2 * length/2)).toInt();
        }

    override var waterCapacity: Double
        get() = volume * 0.8;
        set(value) {}

    override val shape: String = "cylinder"
}