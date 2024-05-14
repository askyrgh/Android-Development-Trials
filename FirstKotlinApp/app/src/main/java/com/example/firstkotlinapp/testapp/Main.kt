package com.example.firstkotlinapp.testapp

fun main() {
    // class example
    buildAquarium();

    // abstract and interface example
    makeFish();

}

fun buildAquarium() {
    val myAquarium = Aquarium(25, 25, 40);
    myAquarium.printSize();
//    myAquarium.volume = 800;
//    myAquarium.printSize();
    val myTowerTank = TowerTank(40,25);
    myTowerTank.printSize();
}

fun makeFish() {
    val shark = Shark()
    val pleco = Plecostomus();

    println("Shark: ${shark.color}");
    shark.eat();
    println("Plecostomus: ${pleco.color}");
    pleco.eat();
}